package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CalculateFareImplementationTest {
	// Target Class
	private CalculateFare cf;


	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Class
		cf = new CalculateFare();
	}
	
	// Calculate Total Fare
	// Valid Parameters
	private Object getValidCalculateTotalFareParams() {
		return new Object[] {
			new Object[] {"titiwangsa"	, "batu kentonmen"	, 15.0},
			new Object[] {"kl sentral"	, "gombak"			, 20.0}
		};
	}
	
	@Test 
	@Parameters (method = "getValidCalculateTotalFareParams")
	public void testCalculateTotalFareValid(String startStation, String endStation, double expectedResult) {
		
		// Run Method
		cf.calculateTotalFare(startStation, endStation);
		double result = cf.getTotalFare();
		
		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters
	private Object getInvalidCalculateTotalFareParams() {
		return new Object[] {
			// Start Station
			new Object[] {null		, "klcc"},			// Null Station
			new Object[] {"INVALID"	, "klcc"},			// Invalid Station
			// End Station
			new Object[] {"klcc"	, null},			// Null Station
			new Object[] {"klcc"	, "INVALID"},		// Invalid Station
			// Same Station
			new Object[] {"klcc"	, "klcc"},
			// Invalid Route
			new Object[] {"kajang"	, "taman melati"},
			// Exceed Distance Limit
			new Object[] {"klcc"	, "sungai buloh"}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculateTotalFareParams")
	public void testCalculateTotalFareInvalid(String startStation, String endStation) {
		
		// Run Method
		cf.calculateTotalFare(startStation, endStation);
	}
	
	
	// Calculate Discounted Fare
	// Valid Parameters
	private Object getValidCalculateDiscountedFareParams() {
		// Sample Array
		List<String> passengerType = new ArrayList<> (Arrays.asList("adult", "child", "senior citizen"));
		List<Integer> passengerQuantity = new ArrayList<> (Arrays.asList(2, 1, 3));
		
		String[] detail1 = {"Passenger Adjustment : 0.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 2\n",
				"Passenger Adjustment : 50.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 1\n",
				"Passenger Adjustment : 50.0 %\nDay Time Adjustment  : 0.0 %\nPassenger Amount     : 3\n"};
		String[] detail2 = {"Passenger Adjustment : 0.0 %\nDay Time Adjustment  : + RM 2.00\nPassenger Amount     : 2\n",
				"Passenger Adjustment : 50.0 %\nDay Time Adjustment  : + RM 2.00\nPassenger Amount     : 1\n",
				"Passenger Adjustment : 50.0 %\nDay Time Adjustment  : + RM 2.00\nPassenger Amount     : 3\n"};
		
		return new Object[] {
			new Object[] {"monday", "0200", "titiwangsa", "batu kentonmen"	, passengerType, passengerQuantity, 60.0, detail1},
			new Object[] {"sunday", "2300", "kl sentral", "gombak"			, passengerType, passengerQuantity, 92.0, detail2},
		};
	}
	
	@Test
	@Parameters (method = "getValidCalculateDiscountedFareParams")
	public void testCalculateDiscountedFareValid(String travelDay, String travelTime, String startStation, String endStation,
			List<String> passengerType, List<Integer> passengerQuantity, double expectedFare, String[] expectedDetail) {
		
		// Run Method
		cf.calculateDiscountedFare(travelDay, travelTime, startStation, endStation, passengerType, passengerQuantity);
		double resultFare = cf.getDiscountedFare();
		List<String> adjDetail = cf.getAdjustmentDetails();
		String[] resultDetail = adjDetail.toArray(new String[adjDetail.size()]);
		
		
		// Compare Result
		assertEquals(resultFare, expectedFare, 0.01);
		assertArrayEquals(resultDetail, expectedDetail);
	}
	
	// Invalid Parameters
	private Object getInvalidCalculateDiscountedFareParams() {
		// Sample Array
		List<String> passengerType = new ArrayList<> (Arrays.asList("adult", "child", "senior citizen"));
		List<Integer> passengerQuantity = new ArrayList<> (Arrays.asList(2, 1, 3));
		
		// Invalid Array
		List<Integer> invalidQuantity = new ArrayList<> (Arrays.asList(1, 3));
		
		// Empty Array
		List<String> emptyType = new ArrayList<> ();
		List<Integer> emptyQuantity = new ArrayList<> ();
		
		return new Object[] {
			// Travel Day
			new Object[] {null		, "0200"	, "titiwangsa"	, "batu kentonmen"	, passengerType	, passengerQuantity },
			new Object[] {"INVALID"	, "0200"	, "titiwangsa"	, "batu kentonmen"	, passengerType	, passengerQuantity },
			// Travel Time
			new Object[] {"monday"	, null		, "titiwangsa"	, "batu kentonmen"	, passengerType	, passengerQuantity },
			new Object[] {"monday"	, "INVALID" , "titiwangsa"	, "batu kentonmen"	, passengerType	, passengerQuantity },
			// Start Station
			new Object[] {"monday"	, "0200"	, null			, "batu kentonmen"	, passengerType	, passengerQuantity },
			new Object[] {"monday"	, "0200"	, "INVALID"		, "batu kentonmen"	, passengerType	, passengerQuantity },
			// End Station
			new Object[] {"monday"	, "0200"	, "titiwangsa"	, null				, passengerType	, passengerQuantity },
			new Object[] {"monday"	, "0200"	, "titiwangsa"	, "INVALID"			, passengerType	, passengerQuantity },
			// Passenger Type
			new Object[] {"monday"	, "0200"	, "titiwangsa"	, "batu kentonmen"	, null			, passengerQuantity },
			new Object[] {"monday"	, "0200"	, "titiwangsa"	, "batu kentonmen"	, emptyType		, passengerQuantity },
			// Passenger Quantity
			new Object[] {"monday"	, "0200"	, "titiwangsa"	, "batu kentonmen"	, passengerType	, null				},
			new Object[] {"monday"	, "0200"	, "titiwangsa"	, "batu kentonmen"	, passengerType	, emptyQuantity		},
			// Incorrect Type & Quantity Length
			new Object[] {"monday"	, "0200"	, "titiwangsa"	, "batu kentonmen"	, passengerType	, invalidQuantity	}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculateDiscountedFareParams")
	public void testCalculateDiscountedFareParams(String travelDay, String travelTime, String startStation, String endStation,
			List<String> passengerType, List<Integer> passengerQuantity) {
		
		// Run Method
		cf.calculateDiscountedFare(travelDay, travelTime, startStation, endStation, passengerType, passengerQuantity);
	}


	// Calculate Payment
	// Valid Parameters
	private Object getValidCalculatePaymentParams() {
		return new Object[] {
			new Object[] {10.0, "e-wallet"		, 10.0},
			new Object[] {10.0, "credit card"	, 10.5},
			new Object[] {10.0, "online banking",  9.5}
		};
	}
	
	@Test
	@Parameters (method = "getValidCalculatePaymentParams")
	public void testCalculatePaymentValid(double totalFare, String paymentMethod, double expectedResult) {
		
		// Run Method
		cf.setDiscountedFare(totalFare);
		cf.calculatePayment(paymentMethod);
		double result = cf.getPaymentAmount();
		
		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters
	private Object getInvalidCalculatePaymentParams() {
		return new Object[] {
			// Total Fare
			new Object[] { 0.0, "e-wallet"},
			// Payment Method
			new Object[] {10.0, null},
			new Object[] {10.0, "INVALID"}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculatePaymentParams")
	public void testCalculatePaymentParams(double totalFare, String paymentMethod) {
		
		// Run Method
		cf.setDiscountedFare(totalFare);
		cf.calculatePayment(paymentMethod);
	}
}