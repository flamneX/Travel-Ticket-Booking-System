package utar.edu.my;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class FareAdjustmentTest {
	// Target Class
	FareAdjustment fa;
	
	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Class
		fa = new FareAdjustment();
	}
	
	
	// Validate Passenger Type
	// Valid Parameters
	private Object getValidPassengerTypeParams() {
		return new Object[] {
			// Adult
			new Object[] {"ADULT", "adult"},
			new Object[] {"adult", "adult"},
			// Senior Citizen
			new Object[] {"SENIOR CITIZEN", "senior citizen"},
			new Object[] {"senior citizen", "senior citizen"},
			// Student
			new Object[] {"STUDENT", "student"},
			new Object[] {"student", "student"},
			// Child
			new Object[] {"CHILD", "child"},
			new Object[] {"child", "child"}
		};
	}
	
	@Test
	@Parameters (method = "getValidPassengerTypeParams")
	public void testValidatePassengerTypeValid(String passengerType, String expectedResult) {
		
		// Run Method
		String result = fa.validatePassengerType(passengerType);
		
		// Compare Results
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters
	private Object getInvalidPassengerTypeParams() {
		return new Object[] {
			new Object[] {null},		// Null Passenger Type
			new Object[] {"INVALID"}	// Invalid Passenger Type
		};
	}
		
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidPassengerTypeParams")
	public void testValidatePassengerTypeInvalid(String passengerType) {
			
		// Run Method
		fa.validatePassengerType(passengerType);
	}

	
	// Passenger Adjustment
	// Valid Parameters - Equivalent Partitioning (EP)
	private Object getValidPassengerAdjustmentParamsEP() {
		return new Object[] {
			// ADULT
			new Object[] {3.0, "adult", 1.0},
			new Object[] {8.0, "adult", 1.0},
			// SENIOR CITIZEN
			new Object[] {3.0, "senior citizen", 0.5},
			new Object[] {8.0, "senior citizen", 0.5},
			// Student
			new Object[] {3.0, "student", 0.7},
			new Object[] {8.0, "student", 0.7},
			// Child
			new Object[] {3.0, "child", 0.0},
			new Object[] {8.0, "child", 0.5}
		};
	}
	
	@Test
	@Parameters (method = "getValidPassengerAdjustmentParamsEP")
	public void testPassengerAdjustmentValidEP(double travelDistance, String passengerType, double expectedResult) {
		
		// Run Method
		fa.setTravelDistance(travelDistance);
		double result = fa.passengerAdjustment(passengerType);
		
		// Compare Results
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters - Equivalent Partitioning (EP)
	private Object getInvalidPassengerAdjustmentParamsEP() {
		return new Object[] {
			// Travel Distance
			new Object[] {-5.0, "adult"},		// Invalid Distance Range
			new Object[] {35.0, "adult"},
			// Passenger Type
			new Object[] {1.0, null},			// Null Passenger Type
			new Object[] {1.0, "INVALID"}		// Invalid Passenger Type
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters(method = "getInvalidPassengerAdjustmentParamsEP")
	public void testPassengerAdjustmentInvalidEP(double travelDistance, String passengerType) {
		
		// Run Method
		fa.setTravelDistance(travelDistance);
		fa.passengerAdjustment(passengerType);
	}
	
	// Valid Parameters - Boundary Value Analysis (BVA) 
	private Object getValidPassengerAdjustmentParamsBVA() {
		return new Object[] {
			// ADULT
			new Object[] { 1.0, "adult", 1.0},
			new Object[] { 4.0, "adult", 1.0},
			new Object[] { 5.0, "adult", 1.0},
			new Object[] {30.0, "adult", 1.0},
			// SENIOR CITIZEN
			new Object[] { 1.0, "senior citizen", 0.5},
			new Object[] { 4.0, "senior citizen", 0.5},
			new Object[] { 5.0, "senior citizen", 0.5},
			new Object[] {30.0, "senior citizen", 0.5},
			// Student
			new Object[] { 1.0, "student", 0.7},
			new Object[] { 4.0, "student", 0.7},
			new Object[] { 5.0, "student", 0.7},
			new Object[] {30.0, "student", 0.7},
			// Child
			new Object[] { 1.0, "child", 0.0},
			new Object[] { 4.0, "child", 0.0},
			new Object[] { 5.0, "child", 0.5},
			new Object[] {30.0, "child", 0.5}
		};
	}
	
	@Test
	@Parameters (method = "getValidPassengerAdjustmentParamsBVA")
	public void testPassengerAdjustmentValidBVA(double travelDistance, String passengerType, double expectedResult) {
		
		// Run Method
		fa.setTravelDistance(travelDistance);
		double result = fa.passengerAdjustment(passengerType);
		
		// Compare Results
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters - Boundary Value Analysis (BVA)
	private Object getInvalidPassengerAdjustmentParamsBVA() {
		return new Object[] {
			// Travel Distance
			new Object[] { 0.0	, "adult"},		// Invalid Distance Range
			new Object[] {31.0	, "adult"},
			// Passenger Type
			new Object[] { 1.0	, null},		// Null Passenger Type
			new Object[] { 1.0	, "INVALID"},	// Invalid Passenger Type
		};
	}

	@Test (expected = IllegalArgumentException.class)
	@Parameters(method = "getInvalidPassengerAdjustmentParamsBVA")
	public void testPassengerAdjustmentInvalidBVA(double travelDistance, String passengerType) {
		
		// Run Method
		fa.setTravelDistance(travelDistance);
		fa.passengerAdjustment(passengerType);
	}

	
	// Is Weekend
	// Valid Parameters
	private Object getValidIsWeekendParams() {
		return new Object[] {
			// Monday
			new Object[] {"MONDAY", false},
			new Object[] {"monday", false},
			// Tuesday
			new Object[] {"TUESDAY", false},
			new Object[] {"tuesday", false},
			// Wednesday
			new Object[] {"WEDNESDAY", false},
			new Object[] {"wednesday", false},
			// Thursday
			new Object[] {"THURSDAY", false},
			new Object[] {"thursday", false},
			// Friday
			new Object[] {"FRIDAY", false},
			new Object[] {"friday", false},
			// Saturday
			new Object[] {"SATURDAY", true},
			new Object[] {"saturday", true},
			// Sunday
			new Object[] {"SUNDAY", true},
			new Object[] {"sunday", true}
		};
	}
	
	@Test
	@Parameters (method = "getValidIsWeekendParams")
	public void testIsWeekendValid(String travelDay, boolean expectedResult) {
		
		// Run Method
		boolean result = fa.isWeekend(travelDay);
		
		// Compare Results
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters
	private Object getInvalidIsWeekendParams() {
		return new Object[] {
			new Object[] {null},		// Null Day
			new Object[] {"INVALID"}	// Invalid Day
		};
	}
	
	@Test (expected = IllegalArgumentException.class) 
	@Parameters(method = "getInvalidIsWeekendParams")
	public void testIsWeekendInvalid(String travelDay) {
		
		// Run Method
		fa.isWeekend(travelDay);
	}
	
	
	// Validate Travel Time
	// Valid Parameters
	private Object getValidTravelTimeParams() {
		return new Object[] {
			new Object[] {"0000", 0},
			new Object[] {"1200", 1200},
			new Object[] {"2359", 2359}
		};
	}
	
	@Test
	@Parameters (method = "getValidTravelTimeParams")
	public void testValidateTravelTimeValid(String travelTime, int expectedResult) {
		
		// Run Method
		int result = fa.validateTravelTime(travelTime);
		
		// Compare Results
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters
	private Object getInvalidTravelTimeParams() {
		return new Object[] {
			new Object[] {null},		// Null Travel Time
			new Object[] {"INVALID"},	// Invalid Travel Time
			new Object[] {"-0001",},	// Invalid Time Range
			new Object[] {"2400"},
			new Object[] {"0060"},		// Invalid Time Format
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidTravelTimeParams")
	public void testValidateTravelTimeInvalid(String travelTime) {
		
		// Run Method
		fa.validatePassengerType(travelTime);
	}
	
	
	// Day Time Adjustment
	// Valid Parameters - Equivalent Partitioning (EP)
	private Object getValidDayTimeAdjustmentParamsEP() {
		return new Object[] {
			// Weekday - Monday
			new Object[] {"monday", "0300", 100},
			new Object[] {"monday", "0800", 120},
			new Object[] {"monday", "1300", 100},
			new Object[] {"monday", "1850", 120},
			new Object[] {"monday", "2100", 100},
			new Object[] {"monday", "2300", 2},
			// Weekend - Saturday
			new Object[] {"saturday", "0300", 90},
			new Object[] {"saturday", "0800", 90},
			new Object[] {"saturday", "1300", 90},
			new Object[] {"saturday", "1850", 90},
			new Object[] {"saturday", "2100", 90},
			new Object[] {"saturday", "2300", 2}
		};
	}

	@Test
	@Parameters (method = "getValidDayTimeAdjustmentParamsEP")
	public void testDayTimeAdjustmentValidEP(String travelDay, String travelTime, int expectedResult) {
		
		// Run Method
		int result = fa.dayTimeAdjustment(travelDay, travelTime);
		
		// Compare Results
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters - Equivalent Partitioning (EP)
	private Object getInvalidDayTimeAdjustmentParamsEP() {
		return new Object[] {
			new Object[] {null		, "0000"},		// Null Travel Day
			new Object[] {"INVALID"	, "0000"},		// Invalid Travel Day
			new Object[] {"monday"	, null},		// Null Travel Time
			new Object[] {"monday"	, "INVALID"},	// Invalid Travel Time
			new Object[] {"monday"	, "-0500"},		// Invalid Time Range
			new Object[] {"monday"	, "2500"},
			new Object[] {"monday"	, "0070"}		// Invalid Time Format 
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidDayTimeAdjustmentParamsEP")
	public void testDayTimeAdjustmentInvalidEP(String travelDay, String travelTime) {
		
		// Run Method
		fa.dayTimeAdjustment(travelDay, travelTime);
	}
	
	
	// Day Time Adjustment
	// Valid Parameters - Boundary Value Analysis (BVA)
	private Object getValidDayTimeAdjustmentParamsBVA() {
		return new Object[] {
			// Weekday - Monday
			new Object[] {"monday", "0000", 100},
			new Object[] {"monday", "0629", 100},
			new Object[] {"monday", "0630", 120},
			new Object[] {"monday", "0930", 120},
			new Object[] {"monday", "0931", 100},
			new Object[] {"monday", "1659", 100},
			new Object[] {"monday", "1700", 120},
			new Object[] {"monday", "2000", 120},
			new Object[] {"monday", "2001", 100},
			new Object[] {"monday", "2159", 100},
			new Object[] {"monday", "2200", 2},
			new Object[] {"monday", "2359", 2},
			// Weekend - Saturday
			new Object[] {"saturday", "0000", 90},
			new Object[] {"saturday", "0629", 90},
			new Object[] {"saturday", "0630", 90},
			new Object[] {"saturday", "0930", 90},
			new Object[] {"saturday", "0931", 90},
			new Object[] {"saturday", "1659", 90},
			new Object[] {"saturday", "1700", 90},
			new Object[] {"saturday", "2000", 90},
			new Object[] {"saturday", "2001", 90},
			new Object[] {"saturday", "2159", 90},
			new Object[] {"saturday", "2200", 2},
			new Object[] {"saturday", "2359", 2},
		};
	}
	
	@Test
	@Parameters (method = "getValidDayTimeAdjustmentParamsBVA")
	public void testDayTimeAdjustmentValidBVA(String travelDay, String travelTime, int expectedResult) {
		
		// Run Method
		int result = fa.dayTimeAdjustment(travelDay, travelTime);
		
		// Compare Results
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters - Boundary Value Analysis (BVA)
	private Object getInvalidDayTimeAdjustmentParamsBVA() {
		return new Object[] {
			new Object[] {null		, "0000"},		// Null Travel Day
			new Object[] {"INVALID"	, "0000"},		// Invalid Travel Day
			new Object[] {"monday"	, null},		// Null Travel Time
			new Object[] {"monday"	, "INVALID"},	// Invalid Travel Time
			new Object[] {"monday"	, "-0001"},		// Invalid Time Range
			new Object[] {"monday"	, "2400"},
			new Object[] {"monday"	, "0060"},		// Invalid Time Format
			new Object[] {"monday"	, "0099"}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidDayTimeAdjustmentParamsBVA")
	public void testDayTimeAdjustmentInvalidBVA(String travelDay, String travelTime) {
		
		// Run Method
		fa.dayTimeAdjustment(travelDay, travelTime);
	}

	
	// Validate Payment Method
	// Valid Parameters
	private Object getValidPaymentMethodParams() {
		return new Object[] {
			// e-Wallet
			new Object[] {"E-WALLET", "e-wallet"},
			new Object[] {"e-wallet", "e-wallet"},
			// Credit Card
			new Object[] {"CREDIT CARD", "credit card"},
			new Object[] {"credit card", "credit card"},
			// Online Banking
			new Object[] {"ONLINE BANKING", "online banking"},
			new Object[] {"online banking", "online banking"}
		};
	}

	@Test
	@Parameters (method = "getValidPaymentMethodParams")
	public void testValidatePaymentMethodValid(String paymentMethod, String expectedResult) {
		
		// Run Method
		String result = fa.validatePaymentMethod(paymentMethod);
		
		// Compare Results
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters
	private Object getInvalidPaymentMethodParams() {
		return new Object[] {
			new Object[] {null},		// Null Payment Method
			new Object[] {"INVALID"}	// Invalid Payment Method
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidPaymentMethodParams")
	public void testValidatePaymentMethodInvalid(String paymentMethod) {
		
		// Run Method
		fa.validatePaymentMethod(paymentMethod);
	}
	
	
	// Payment Method Adjustment
	// Valid Parameters
	private Object getValidPaymentMethodAdjustmentParams() {
		return new Object[] {
			new Object[] {"e-wallet", 		1.00},
			new Object[] {"credit card", 	1.05},
			new Object[] {"online banking", 0.95},
		};
	}
	
	@Test
	@Parameters (method = "getValidPaymentMethodAdjustmentParams")
	public void testPaymentMethodAdjustment(String paymentMethod, double expectedResult) {
		
		// Run Method
		double result = fa.paymentMethodAdjustment(paymentMethod);
		
		// Compare Results
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters
	private Object getInvalidPaymentMethodAdjustmentParams() {
		return new Object[] {
			new Object[] {null},		// Null Payment Method
			new Object[] {"INVALID"}	// Invalid Payment Method
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidPaymentMethodAdjustmentParams")
	public void testPaymentMethodAdjustmentInvalid(String paymentMethod) {
		
		// Run Method
		fa.paymentMethodAdjustment(paymentMethod);
	}
}