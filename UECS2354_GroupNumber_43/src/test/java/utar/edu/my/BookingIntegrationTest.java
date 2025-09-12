package utar.edu.my;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class BookingIntegrationTest {

	// Get Total Fare
	// Valid Parameters
	private Object getValidGetTotalFareParams() {
		return new Object[] {
				new Object[] {"kl sentral"		, "mid valley"		,  2.0},
				new Object[] {"kl sentral"		, "subang jaya"		, 15.0},
				new Object[] {"subang jaya"		, "shah alam"		,  5.0},
				new Object[] {"bangsar"			, "kl sentral"		,  2.0},
				new Object[] {"kl sentral"		, "kepong sentral"	, 10.0}
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
	private Object getValidGetDiscountedFareParams() {
		// Sample Array List
		List<String> passengerType1 = new ArrayList<> (Arrays.asList(new String[] {"adult",				"child", 			"senior citizen"}));
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
			new Object[] {"monday"		, "0300",	"kl sentral"	, "mid valley"	  	, passengerType1, passengerQuantity1,  4.00, detail1},
			new Object[] {"wednesday"	, "0800",	"kl sentral"	, "subang jaya"		, passengerType2, passengerQuantity2, 72.00, detail2},
			new Object[] {"friday"		, "1300",	"subang jaya"	, "shah alam"  		, passengerType3, passengerQuantity3, 21.00, detail3},
			new Object[] {"saturday"	, "1850",	"bangsar"		, "kl sentral"		, passengerType4, passengerQuantity4,  5.58, detail4},	
			new Object[] {"sunday"		, "2200",	"kl sentral"	, "kepong sentral"	, passengerType5, passengerQuantity5, 57.00, detail5}
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
	private Object getInvalidGetDiscountedFareParams() {
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
	@Parameters (method = "getInvalidGetDiscountedFareParams")
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
		// Sample Array
		List<String> passengerType = new ArrayList<> (Arrays.asList("adult", "child", "senior citizen"));
		List<Integer> passengerQuantity = new ArrayList<> (Arrays.asList(2, 1, 3));
		
		// Setup Class
		Booking b = new Booking(null, "monday", "0200", "titiwangsa", "batu kentonmen", passengerType, passengerQuantity, paymentMethod);
		
		// Run Method
		b.getDiscountedFare();
		b.makePayment();
		String result = b.getBookingStatus();
		
		// Compare Result
		assertEquals(result, "Confirmed Booking");
	}
	
	// Invalid Parameters
	private Object getInvalidMakePaymentParams() {
		return new Object[] {
			new Object[] {null},		// Null Method Method
			new Object[] {"INVALID"},	// Invalid Payment Method
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidMakePaymentParams")
	public void testMakePaymentInvalid1(String paymentMethod) {

		// Sample Array
		List<String> passengerType = new ArrayList<> (Arrays.asList("adult", "child", "senior citizen"));
		List<Integer> passengerQuantity = new ArrayList<> (Arrays.asList(2, 1, 3));
		
		// Setup Class
		Booking b = new Booking(null, "monday", "0200", "titiwangsa", "batu kentonmen", passengerType, passengerQuantity, paymentMethod);
		
		// Run Method
		b.makePayment();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testMakePaymentInvalid2() {
			
		// Setup Class
		Booking b = new Booking(null, null, null, null, null, null, null, "e-wallet");
			
		// Run Method
		b.makePayment();		// Null Fare
	}
}