package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CalculateFareUnitTest {
	// Target Class
	private RouteInfo riMock;
	private FareAdjustment faMock;
	private CalculateFare cf;


	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Class
		riMock = mock(RouteInfo.class);
		faMock = mock(FareAdjustment.class);
		cf = new CalculateFare(riMock, faMock);
	}


	// Calculate Total Fare
	// Valid Parameters - Equivalent Partitioning (EP)
	private Object getValidCalculateTotalFareParamsEP() {
		return new Object[] {
			new Object[] { 3.0,  2.0},	//  0 < x <=  5
			new Object[] { 8.0,  5.0},	//  5 < x <= 10
			new Object[] {13.0, 10.0},	// 10 < x <= 15
			new Object[] {18.0, 15.0},	// 15 < x <= 20
			new Object[] {25.0, 20.0},	// 20 < x <= 30
		};
	}
	
	@Test
	@Parameters (method = "getValidCalculateTotalFareParamsEP")
	public void testCalculateTotalFareValidEP(double distance, double expectedResult) {
		
		// Mock Route Info Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		
		// Run Method
		cf.calculateTotalFare("VALID", "VALID");
		double result = cf.getTotalFare();
		
		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Values - Equivalent Partitioning (EP)
	private Object getInvalidCalculateTotalFareParamsEP() {
		return new Object[] {
			new Object[] {-5.0},	// Invalid Distance Range
			new Object[] {35.0},
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculateTotalFareParamsEP")
	public void testCalculateTotalFareInvalidEP(double distance) {

		// Mock Route Info Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		
		// Run Method
		cf.calculateTotalFare("INVALID", "INVALID");
	}

	// Valid Parameters - Boundary Value Analysis (BVA)
	private Object getValidCalculateTotalFareParamsBVA() {
		return new Object[] {
			new Object[] { 1.0,  2.0},	//  0 < x <=  5
			new Object[] { 5.0,  2.0},
			new Object[] { 6.0,  5.0},	//  5 < x <= 10
			new Object[] {10.0,  5.0},
			new Object[] {11.0, 10.0},	// 10 < x <= 15
			new Object[] {15.0, 10.0},
			new Object[] {16.0, 15.0},	// 15 < x <= 20
			new Object[] {20.0, 15.0},
			new Object[] {21.0, 20.0},	// 20 < x <= 30
			new Object[] {30.0, 20.0},
		};
	}
	
	@Test
	@Parameters (method = "getValidCalculateTotalFareParamsBVA")
	public void testCalculateTotalFareValidBVA(double distance, double expectedResult) {
		
		// Mock Route Info Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		
		// Run Method
		cf.calculateTotalFare("VALID", "VALID");
		double result = cf.getTotalFare();
		
		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters - Boundary Value Analysis (BVA)
	private Object getInvalidCalculateTotalFareParamsBVA() {
		return new Object[] {
			new Object[] {0.5},		// 0 < x <= 30 (Valid)
			new Object[] {30.5}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculateTotalFareParamsBVA")
	public void testCalculateTotalFareInvalidBVA(double distance) {
		
		// Mock Route Info Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		
		// Run Method
		cf.calculateTotalFare("INVALID", "INVALID");
	}


	// Calculate Discounted Fare
	// Valid Parameters
	private Object getValidCalculateDiscountedFareParams() {
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
		
		String[] detail1 = {
				"Passenger Adjustment : 0.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 1\n",
				"Passenger Adjustment : -50.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 1\n",
				"Passenger Adjustment : -50.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 1\n"
				};
		String[] detail2 = {
				"Passenger Adjustment : -50.0 %\nDay Time Adjustment  : 20.0 %\nPassenger Amount     : 2\n",
				"Passenger Adjustment : 0.0 %\nDay Time Adjustment  : 20.0 %\nPassenger Amount     : 2\n",
				"Passenger Adjustment : -50.0 %\nDay Time Adjustment  : 20.0 %\nPassenger Amount     : 2\n"
				};
		String[] detail3 = {
				"Passenger Adjustment : -30.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 1\n",
				"Passenger Adjustment : 0.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 2\n",
				"Passenger Adjustment : -50.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 3\n"
				};
		String[] detail4 = {
				"Passenger Adjustment : -50.0 %\nDay Time Adjustment  : -10.0 %\nPassenger Amount     : 2\n",
				"Passenger Adjustment : -30.0 %\nDay Time Adjustment  : -10.0 %\nPassenger Amount     : 3\n",
				"Passenger Adjustment : -100.0 %\nDay Time Adjustment  : -10.0 %\nPassenger Amount     : 1\n"
				};
		String[] detail5 = {
				"Passenger Adjustment : 0.0 %\nDay Time Adjustment  : + RM 2.00\nPassenger Amount     : 3\n",
				"Passenger Adjustment : -50.0 %\nDay Time Adjustment  : + RM 2.00\nPassenger Amount     : 1\n",
				"Passenger Adjustment : -50.0 %\nDay Time Adjustment  : + RM 2.00\nPassenger Amount     : 2\n"
				};
		
		return new Object[] {
			new Object[] {passengerType1, passengerQuantity1,  5.0, 1.00, 0.50, 0.50, 100,   4.00, detail1},
			new Object[] {passengerType2, passengerQuantity2, 10.0, 0.50, 1.00, 0.50, 120,  24.00, detail2},
			new Object[] {passengerType3, passengerQuantity3, 15.0, 0.70, 1.00, 0.50, 100,  42.00, detail3},
			new Object[] {passengerType4, passengerQuantity4, 20.0, 0.50, 0.70, 0.00,  90,  41.85, detail4},
			new Object[] {passengerType5, passengerQuantity5, 25.0, 1.00, 0.50, 0.50,   2, 102.00, detail5},
		};
	}
	
	@Test
	@Parameters (method = "getValidCalculateDiscountedFareParams")
	public void testCalculateDiscountedFareValidFare(List<String> passengerType, List<Integer> passengerQuantity, double distance, 
			double pDiscount1, double pDiscount2, double pDiscount3, int dayTimeDiscount, double expectedFare, String[] expectedDetail) {
		
		// Mock Route Distance Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		when(faMock.passengerAdjustment(anyString())).thenReturn(pDiscount1).thenReturn(pDiscount2).thenReturn(pDiscount3);
		when(faMock.dayTimeAdjustment(anyString(), anyString())).thenReturn(dayTimeDiscount);
		
		// Run Method
		cf.calculateDiscountedFare("day", "time", "startStation", "endStation", passengerType, passengerQuantity);
		double resultFare = cf.getDiscountedFare();
		List<String> adjDetail = cf.getAdjustmentDetails();
		String[] resultDetail = adjDetail.toArray(new String[adjDetail.size()]);
		
		// Compare Result
		assertEquals(resultFare, expectedFare, 0.01);
		assertArrayEquals(resultDetail, expectedDetail);
	}
	
	// Invalid Parameters
	private Object getInvalidCalculateDiscountedFareParams() {
		// Sample Array List
		List<String> passengerType = new ArrayList<> (Arrays.asList(new String[] {"", "", ""}));
		List<Integer> passengerQuantity = new ArrayList<> (Arrays.asList(new Integer[] {0, 0, 0, 0}));
		// Empty Array List
		List<String> emptyType = new ArrayList<> ();
		List<Integer> emptyQuantity = new ArrayList<> ();
		
		return new Object[] {
			new Object[] {null			, passengerQuantity},	// Null Passenger Type List
			new Object[] {emptyType		, passengerQuantity},	// Empty Passenger Type List
			new Object[] {passengerType	, null},				// Null Passenger Quantity
			new Object[] {passengerType	, emptyQuantity},		// Empty Passenger Quantity
			new Object[] {passengerType	, passengerQuantity}	// Different Passenger Type & Quantity Length
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculateDiscountedFareParams")
	public void testCalculateDiscountedFareInvalid(List<String> passengerType, List<Integer> passengerQuantity) {

		// Run Method
		cf.calculateDiscountedFare("INVALID", "INVALID", "INVALID", "INVALID", passengerType, passengerQuantity);
	}


	// Calculate Payment
	// Valid Parameters
	private Object getValidCalculatePaymentParams() {
		return new Object[] {
			new Object[] {10.0, "e-wallet", 		1.00, 10.0},
			new Object[] {10.0, "credit card", 		1.05, 10.5},
			new Object[] {10.0, "online banking",	0.95,  9.5},
		};
	}
	
	@Test
	@Parameters (method = "getValidCalculatePaymentParams")
	public void testCalculatePaymentValid(double totalFare, String paymentMethod, double paymentAdjustment, double expectedResult) {
		
		// Mock Payment Adjustment Output
		when(faMock.paymentMethodAdjustment(anyString())).thenReturn(paymentAdjustment);
		
		// Run Method
		cf.setDiscountedFare(totalFare);
		cf.calculatePayment(paymentMethod);
		double result = cf.getPaymentAmount();
		
		// Compare Results
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters
	private Object getInvalidCalculatePaymentParams() {
		return new Object[] {
			new Object[] {0, "E-Wallet"}		// Invalid Total Fare
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculatePaymentParams")
	public void testCalculatePaymentInvalid(double totalFare, String paymentMethod) {
		
		// Run Method
		cf.setDiscountedFare(totalFare);
		cf.calculatePayment(paymentMethod);
	}
}