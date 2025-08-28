package utar.edu.my;

import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RouteInfoTest {
	// Initiate Route Class
	RouteInfo ri = new RouteInfo();
	
	// Route Info
	// Valid Parameters
	private Object getValidGetRouteDistanceParams() {
		return new Object[] {
			// Random Station
			new Object[] {"Rawang"		, "Bandaraya"		, 36.0},
			new Object[] {"Taman Melati", "Gombak"			,  4.0},
			new Object[] {"Shah Alam"	, "Masjid Jamek"	, 96.5},
			new Object[] {"Sungai Buloh", "Setiawangsa"		, 38.0},
			new Object[] {"Sentul Timur", "Batu Kentonmen"	, 23.0},
			new Object[] {"Serdang"		, "Semenyih Sentral", 16.0},
			new Object[] {"Mid Valley"	, "KL Sentral"		,  5.0},
			new Object[] {"Subang Jaya"	, "Wangsa Maju"		, 38.5},
			new Object[] {"Bangsar"		, "Ampang Park"		, 77.5},
			new Object[] {"KLCC"		, "Kepong Sentral"	, 62.0}
		};
	}
	
	@Test
	@Parameters (method = "getValidGetRouteDistanceParams")
	public void testGetRouteDistanceValidValues(String startStation, String endStation, double expectedResult) {
		double result = ri.getRouteDistance(startStation, endStation);
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters
	private Object getInvalidGetRouteDistanceParams() {
		return new Object[] {
			// Invalid First Station
			new Object[] {null, "KLCC"},		// Null Station
			new Object[] {"INVALID", "KLCC"},	// Invalid Station
			// Invalid Second Station
			new Object[] {"KLCC", null},		// Null Station
			new Object[] {"KLCC", "INVALID"}	// Invalid Station
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidGetRouteDistanceParams")
	public void testGetRouteDistanceInvalidValues(String startStation, String endStation) {
		ri.getRouteDistance(startStation, endStation);
	}
}
