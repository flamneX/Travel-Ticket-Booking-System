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
public class BookingImplementationTest {

	// Get Total Fare
	// Valid Parameters
	private Object getValidGetTotalFareParams() {
		return new Object[] {
			new Object[] {"titiwangsa"	, "batu kentonmen"	, 15.0},
			new Object[] {"kl sentral"	, "gombak"			, 20.0}
		};
	}
	
	@Test
	@Parameters (method = "getValidGetTotalFareParams")
	public void testGetTotalFareValid(String startStation, String endStation, double expectedResult) {

		// Setup Class
		Booking b = new Booking(null, null, null, startStation, endStation, null, null,null);
		
		// Run Method
		double result = b.getTotalFare();
		
		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters
	private Object getInvalidGetTotalFareParams() {
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
	@Parameters (method = "getInvalidGetTotalFareParams")
	public void testGetTotalFareInvalid(String startStation, String endStation) {

		// Setup Class
		Booking b = new Booking(null, null, null, startStation, endStation, null, null,null);
		
		// Run Method
		b.getTotalFare();
	}
	
	
	// Get Discounted Fare + Get Discount Details
	// Valid Parameters
	private Object getValidGetDiscountFareParams() {
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
	@Parameters (method = "getValidGetDiscountedFareParams")
	public void testGetDiscountedFareValid(String travelDay, String travelTime, String startStation, String endStation,
			List<String> passengerType, List<Integer> passengerQuantity, double expectedFare, String[] expectedDetail) {
		
		// Setup Class
		Booking b = new Booking(null, travelDay, travelTime, startStation, endStation, passengerType, passengerQuantity, null);
		
		// Run Method
		double resultFare = b.getDiscountedFare();
		List<String> adjDetail = b.getDiscountDetails();
		String[] resultDetail = adjDetail.toArray(new String[adjDetail.size()]);
		
		// Compare Result
		assertEquals(resultFare, expectedFare, 0.01);
		assertArrayEquals(resultDetail, expectedDetail);
	}
	
	// Get Discounted Fare
	// Invalid Parameters
	private Object getInvalidGetDiscountedParams() {
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
	@Parameters (method = "getInvalidGetDiscountParams")
	public void testGetDiscountedFareParams(String travelDay, String travelTime, String startStation, String endStation,
			List<String> passengerType, List<Integer> passengerQuantity) {
		
		// Setup Class
		Booking b = new Booking(null, travelDay, travelTime, startStation, endStation, passengerType, passengerQuantity, null);
		
		// Run Method
		b.getDiscountedFare();
	}
	
	// Get Discount Details
	// Invalid Parameters
	@Test (expected = IllegalArgumentException.class)
	public void testGetDiscountDetailParams() {
		
		// Setup Class
		Booking b = new Booking(null, null, null, null, null, null, null, null);	// Null Details
		
		// Run Method
		b.getDiscountedFare();
	}
	
	// Make Payment
	// Valid Parameters
	private Object getValidMakePaymentParams() {
		return new Object[] {
			new Object[] {10.0, 1.0, 10.0},
			new Object[] {10.0, 0.5,  5.0},
		};
	}
}
