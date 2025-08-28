package utar.edu.my;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ApplyDiscountSurchargeTest {
	//Initiate Discount Class
	ApplyDiscountSurcharge ads = new ApplyDiscountSurcharge();
	
	// Passenger Discount
	// Valid Parameters - Equivalent Partitioning (EP)
	private Object getValidPassengerDiscountParamsEP() {
		return new Object[] {
			// ADULT
			new Object[] {3.0, "ADULT", 1.0},
			new Object[] {8.0, "ADULT", 1.0},
			new Object[] {3.0, "adult", 1.0},
			new Object[] {8.0, "adult", 1.0},
			// SENIOR CITIZEN
			new Object[] {3.0, "SENIOR", 0.5},
			new Object[] {8.0, "SENIOR", 0.5},
			new Object[] {3.0, "senior", 0.5},
			new Object[] {8.0, "senior", 0.5},
			// Student
			new Object[] {3.0, "STUDENT", 0.7},
			new Object[] {8.0, "STUDENT", 0.7},
			new Object[] {3.0, "student", 0.7},
			new Object[] {8.0, "student", 0.7},
			// Child
			new Object[] {3.0, "CHILD", 0.0},
			new Object[] {8.0, "CHILD", 0.5},
			new Object[] {3.0, "child", 0.0},
			new Object[] {8.0, "child", 0.5},
		};
	}
	
	@Test
	@Parameters (method = "getValidPassengerDiscountParamsEP")
	public void testPassengerDiscountValidValuesEP(double travelDistance, String passengerType, double expectedResult) {
		double result = ads.passengerDiscount(travelDistance, passengerType);
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters - Equivalent Partitioning (EP)
	private Object getInvalidPassengerDiscountParamsEP() {
		return new Object[] {
			// Travel Distance
			new Object[] {-5.0, "ADULT"},		// Invalid Distance Range
			new Object[] {35.0, "ADULT"},
			// Passenger Type
			new Object[] {1.0, null},			// Null Passenger Type
			new Object[] {1.0, "INVALID"},	// Invalid Passenger Type
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters(method = "getInvalidPassengerDiscountParamsEP")
	public void testPassengerDiscountInvalidValuesEP(double travelDistance, String passengerType) {
		ads.passengerDiscount(travelDistance, passengerType);
	}
	
	// Valid Parameters - Boundary Value Analysis (BVA) 
	private Object getValidPassengerDiscountParamsBVA() {
		return new Object[] {
			// ADULT
			new Object[] {1.0, "ADULT", 1.0},
			new Object[] {4.0, "ADULT", 1.0},
			new Object[] {5.0, "ADULT", 1.0},
			new Object[] {30.0, "ADULT", 1.0},
			new Object[] {1.0, "adult", 1.0},
			new Object[] {4.0, "adult", 1.0},
			new Object[] {5.0, "adult", 1.0},
			new Object[] {30.0, "adult", 1.0},
			// SENIOR CITIZEN
			new Object[] {1.0, "SENIOR", 0.5},
			new Object[] {4.0, "SENIOR", 0.5},
			new Object[] {5.0, "SENIOR", 0.5},
			new Object[] {30.0, "SENIOR", 0.5},
			new Object[] {1.0, "senior", 0.5},
			new Object[] {4.0, "senior", 0.5},
			new Object[] {5.0, "senior", 0.5},
			new Object[] {30.0, "senior", 0.5},
			// Student
			new Object[] {1.0, "STUDENT", 0.7},
			new Object[] {4.0, "STUDENT", 0.7},
			new Object[] {5.0, "STUDENT", 0.7},
			new Object[] {30.0, "STUDENT", 0.7},
			new Object[] {1.0, "student", 0.7},
			new Object[] {4.0, "student", 0.7},
			new Object[] {5.0, "student", 0.7},
			new Object[] {30.0, "student", 0.7},
			// Child
			new Object[] {1.0, "CHILD", 0.0},
			new Object[] {4.0, "CHILD", 0.0},
			new Object[] {5.0, "CHILD", 0.5},
			new Object[] {30.0, "CHILD", 0.5},
			new Object[] {1.0, "child", 0.0},
			new Object[] {4.0, "child", 0.0},
			new Object[] {5.0, "child", 0.5},
			new Object[] {30.0, "child", 0.5},
		};
	}
	
	@Test
	@Parameters (method = "getValidPassengerDiscountParamsBVA")
	public void testPassengerDiscountValidValuesBVA(double travelDistance, String passengerType, double expectedResult) {
		double result = ads.passengerDiscount(travelDistance, passengerType);
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters - Boundary Value Analysis (BVA)
	private Object getInvalidPassengerDiscountParamsBVA() {
		return new Object[] {
			// Travel Distance
			new Object[] {0.0, "ADULT"},		// Invalid Distance Range
			new Object[] {31.0, "ADULT"},
			// Passenger Type
			new Object[] {1.0, null},			// Null Passenger Type
			new Object[] {1.0, "INVALID"},	// Invalid Passenger Type
		};
	}
		
	@Test (expected = IllegalArgumentException.class)
	@Parameters(method = "getInvalidPassengerDiscountParamsBVA")
	public void testPassengerDiscountInvalidValuesBVA(double travelDistance, String passengerType) {
		ads.passengerDiscount(travelDistance, passengerType);
	}

	// Is Weekend
	// Valid Parameters
	private Object getValidIsWeekendParams() {
		return new Object[] {
			// Monday
			new Object[] {"MONDAY", false},
			new Object[] {"monday", false},
			new Object[] {"MON", false},
			new Object[] {"mon", false},
			// Tuesday
			new Object[] {"TUESDAY", false},
			new Object[] {"tuesday", false},
			new Object[] {"TUE", false},
			new Object[] {"tue", false},
			// Wednesday
			new Object[] {"WEDNESDAY", false},
			new Object[] {"wednesday", false},
			new Object[] {"WED", false},
			new Object[] {"wed", false},
			// Thursday
			new Object[] {"THURSDAY", false},
			new Object[] {"thursday", false},
			new Object[] {"THU", false},
			new Object[] {"thu", false},
			// Friday
			new Object[] {"FRIDAY", false},
			new Object[] {"friday", false},
			new Object[] {"FRI", false},
			new Object[] {"fri", false},
			// Saturday
			new Object[] {"SATURDAY", true},
			new Object[] {"saturday", true},
			new Object[] {"SAT", true},
			new Object[] {"sat", true},
			// Sunday
			new Object[] {"SUNDAY", true},
			new Object[] {"sunday", true},
			new Object[] {"SUN", true},
			new Object[] {"sun", true},
		};
	}
	
	@Test
	@Parameters (method = "getValidIsWeekendParams")
	public void testIsWeekendValidValues(String travelDay, boolean expectedResult) {
		boolean result = ads.isWeekend(travelDay);
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters
	private Object getInvalidIsWeekendParams() {
		return new Object[] {
			new Object[] {"INVALID"},
			new Object[] {null}
		};
	}
	
	@Test (expected = IllegalArgumentException.class) 
	@Parameters(method = "getInvalidIsWeekendParams")
	public void testDayTimeDiscountInvalidValues(String travelDay) {
		ads.isWeekend(travelDay);
	}
	
	// Day Time Discount
	// Valid Parameters - Equivalent Partitioning (EP)
	private Object getValidDayTimeDiscountParamsEP() {
		return new Object[] {
			// Weekday
			new Object[] {false, "0300", 100},
			new Object[] {false, "0800", 120},
			new Object[] {false, "1300", 100},
			new Object[] {false, "1850", 120},
			new Object[] {false, "2100", 100},
			new Object[] {false, "2300", 2},
			// Weekend
			new Object[] {true, "0300", 90},
			new Object[] {true, "0800", 90},
			new Object[] {true, "1300", 90},
			new Object[] {true, "1850", 90},
			new Object[] {true, "2100", 90},
			new Object[] {true, "2300", 2}
		};
	}

	@Test
	@Parameters (method = "getValidDayTimeDiscountParamsEP")
	public void testDayTimeDiscountValidValuesEP(boolean weekend, String travelTime, int expectedResult) {
		int result = ads.dayTimeDiscount(weekend, travelTime);
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters - Equivalent Partitioning (EP)
	private Object getInvalidDayTimeDiscountParamsEP() {
		return new Object[] {
			// Null Boolean
			new Object[] {null, "0000"},
			// Invalid Time
			new Object[] {true, "INVALID"},
			// Null Time
			new Object[] {true, null},
			// Invalid Range
			new Object[] {true, "-500"},
			new Object[] {true, "2500"},
			new Object[] {true, "0070"} 
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidDayTimeDiscountParamsEP")
	public void testDayTimeDiscountInvalidValuesEP(boolean weekend, String travelTime) {
		ads.dayTimeDiscount(weekend, travelTime);
	}
	
	// Day Time Discount
	// Valid Parameters - Boundary Value Analysis (BVA)
	private Object getValidDayTimeDiscountParamsBVA() {
		return new Object[] {
			// Weekday
			new Object[] {false, "0000", 100},
			new Object[] {false, "0629", 100},
			new Object[] {false, "0630", 120},
			new Object[] {false, "0930", 120},
			new Object[] {false, "0931", 100},
			new Object[] {false, "1659", 100},
			new Object[] {false, "1700", 120},
			new Object[] {false, "2000", 120},
			new Object[] {false, "2001", 100},
			new Object[] {false, "2159", 100},
			new Object[] {false, "2200", 2},
			new Object[] {false, "2359", 2},
			// Weekend
			new Object[] {true, "0000", 90},
			new Object[] {true, "0629", 90},
			new Object[] {true, "0630", 90},
			new Object[] {true, "0930", 90},
			new Object[] {true, "0931", 90},
			new Object[] {true, "1659", 90},
			new Object[] {true, "1700", 90},
			new Object[] {true, "2000", 90},
			new Object[] {true, "2001", 90},
			new Object[] {true, "2159", 90},
			new Object[] {true, "2200", 2},
			new Object[] {true, "2359", 2},
		};
	}
	
	@Test
	@Parameters (method = "getValidDayTimeDiscountParamsBVA")
	public void testDayTimeDiscountValidValuesBVA(boolean weekend, String travelTime, int expectedResult) {
		int result = ads.dayTimeDiscount(weekend, travelTime);
		assertEquals(result, expectedResult);
	}
	
	// Invalid Parameters - Equivalent Partitioning (EP)
	private Object getInvalidDayTimeDiscountParamsBVA() {
		return new Object[] {
			// Null Boolean
			new Object[] {null, "0000"},
			// Invalid Time
			new Object[] {true, "INVALID"},
			// Null Time
			new Object[] {true, null},
			// Invalid Range
			new Object[] {null, "-0001"},
			new Object[] {null, "0070"},
			new Object[] {null, "2401"}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidDayTimeDiscountParamsBVA")
	public void testDayTimeDiscountInvalidValuesBVA(boolean weekend, String travelTime) {
		ads.dayTimeDiscount(weekend, travelTime);
	}
}