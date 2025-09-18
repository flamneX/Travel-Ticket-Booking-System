package utar.edu.my;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class BookingUnitTest {
	// Target Class
	private CalculateFare cfMock;
	private Payment pMock;
	private Booking b;


	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Class
		cfMock= mock(CalculateFare.class);
		pMock = mock(Payment.class);
		b = new Booking(cfMock, pMock);
	}


	// Get Total Fare
	public Object getTotalFareValidParams() {
		return new Object[] {
			new Object[] {"kl sentral"		, "mid valley"},
			new Object[] {"kl sentral"		, "subang jaya"},
			new Object[] {"subang jaya"		, "shah alam"},
			new Object[] {"bangsar"			, "kl sentral"},
			new Object[] {"kl sentral"		, "kepong sentral"}
		};
	}
	
	@Test
	@Parameters (method = "getTotalFareValidParams")
	public void testGetTotalFare(String startStation, String endStation) {
		
		// Set Parameters
		b.setStartStation(startStation);
		b.setEndStation(endStation);
		
		// Run Method
		b.getTotalFare();
		
		// Verify Method Execution
		verify(cfMock, times(1)).calculateTotalFare(startStation, endStation);
		verify(cfMock, times(1)).getTotalFare();
	}


	// Get Discounted Fare
	private Object getDiscountedFareValidParams() {

		// Sample Array List
		List<String> passengerType1 = new ArrayList<> (Arrays.asList(new String[] {"adult", 			"child", 			"senior citizen"}));
		List<String> passengerType2 = new ArrayList<> (Arrays.asList(new String[] {"senior citizen", 	"adult", 			"child"}));
		List<String> passengerType3 = new ArrayList<> (Arrays.asList(new String[] {"student", 			"adult", 			"child"}));
		List<String> passengerType4 = new ArrayList<> (Arrays.asList(new String[] {"senior citizen", 	"student", 			"child"}));
		List<String> passengerType5 = new ArrayList<> (Arrays.asList(new String[] {"adult", 			"senior citizen", 	"child"}));
		
		List<Integer> passengerQuantity1 = new ArrayList<> (Arrays.asList(new Integer[] {1, 1, 1}));
		List<Integer> passengerQuantity2 = new ArrayList<> (Arrays.asList(new Integer[] {2, 2, 2}));
		List<Integer> passengerQuantity3 = new ArrayList<> (Arrays.asList(new Integer[] {1, 2, 3}));
		List<Integer> passengerQuantity4 = new ArrayList<> (Arrays.asList(new Integer[] {2, 3, 1}));
		List<Integer> passengerQuantity5 = new ArrayList<> (Arrays.asList(new Integer[] {3, 1, 2}));
		
		return new Object[] {
			new Object[] {"monday"		, "0300",	"kl sentral"	, "mid valley"	  	, passengerType1, passengerQuantity1},
			new Object[] {"wednesday"	, "0800",	"kl sentral"	, "subang jaya"		, passengerType2, passengerQuantity2},
			new Object[] {"friday"		, "1300",	"subang jaya"	, "shah alam"  		, passengerType3, passengerQuantity3},
			new Object[] {"saturday"	, "1850",	"bangsar"		, "kl sentral"		, passengerType4, passengerQuantity4},	
			new Object[] {"sunday"		, "2200",	"kl sentral"	, "kepong sentral"	, passengerType5, passengerQuantity5}
		};
	}
	
	@Test
	@Parameters (method = "getDiscountedFareValidParams")
	public void testGetDiscountedFare(String travelDay, String travelTime, String startStation, String endStation, List<String> passengerType, List<Integer> passengerQuantity) {
		
		// Set Parameters
		b.setTravelDay(travelDay);
		b.setTravelTime(travelTime);
		b.setStartStation(startStation);
		b.setEndStation(endStation);
		b.setPassengerType(passengerType);
		b.setPassengerQuantity(passengerQuantity);
		
		// Run Method
		b.getDiscountedFare();
		
		// Verify Method Execution
		verify(cfMock, times(1)).calculateDiscountedFare(travelDay, travelTime, startStation, endStation, passengerType, passengerQuantity);
		verify(cfMock, times(1)).getDiscountedFare();
	}


	// Get Discounted Detail
	// Valid Parameters
	@Test
	public void testGetDiscountDetailValid() {
		
		// Mock Output
		when(cfMock.getAdjustmentDetails()).thenReturn(new ArrayList<String> (Arrays.asList(new String[]{""})));

		// Run Method
		b.getDiscountDetails();
			
		// Verify Method Execution
		verify(cfMock, times(1)).getAdjustmentDetails();
	}
	
	// Invalid Parameters
	@Test (expected = IllegalArgumentException.class)
	public void testGetDiscountDetailInvalid() {
		// Run Method
		b.getDiscountDetails();			// Null Details
	}


	// Make Payment
	private Object getMakePaymentValidParams() {
		return new Object[] {
			new Object[] {"e-wallet"},
			new Object[] {"credit card"},
			new Object[] {"online banking"}
		};
	}
	
	@Test
	@Parameters (method = "getMakePaymentValidParams")
	public void testMakePaymentValid(String paymentMethod) {
		
		// Set Parameters
		b.setPaymentMethod(paymentMethod);
		
		// Mock Status Output
		when(pMock.getStatus()).thenReturn("Paid");
		
		// Run Method
		b.makePayment();
		
		// Verify Method Execution
		verify(cfMock, times(1)).calculatePayment(paymentMethod);
		verify(cfMock, times(1)).getPaymentAmount();
		verify(pMock, times(1)).makePayment(anyDouble());
		verify(pMock, times(1)).getStatus();
		verify(pMock, times(1)).emailReceipt();
	}
}