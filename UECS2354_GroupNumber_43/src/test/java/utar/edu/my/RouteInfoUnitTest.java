package utar.edu.my;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RouteInfoUnitTest {
	// Target Class
	private RouteInfo ri;


	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Class
		ri = new RouteInfo();
	}
	
	
	// Validate Station
	// Valid Parameters
	private Object getValidStationNameParams() {
		return new Object[] {
			new Object[] {"kl sentral"		, "kl sentral"		}, 
			new Object[] {"mid valley"		, "mid valley"		}, 
			new Object[] {"subang jaya"		, "subang jaya"		}, 
			new Object[] {"shah alam"		, "shah alam"		},
			new Object[] {"bangsar"			, "bangsar"			},
			new Object[] {"kepong sentral"	, "kepong sentral"	},
			new Object[] {"sentul timur"	, "sentul timur"	},
			new Object[] {"titiwangsa"		, "titiwangsa"		},
			new Object[] {"ampang park"		, "ampang park"		}, 
			new Object[] {"klcc"			, "klcc"			},
			new Object[] {"masjid jamek"	, "masjid jamek"	},
			new Object[] {"bandaraya"		, "bandaraya"		},
			new Object[] {"batu kentonmen"	, "batu kentonmen"	},
			new Object[] {"rawang"			, "rawang"			},
			new Object[] {"sungai buloh"	, "sungai buloh"	},
			new Object[] {"serdang"			, "serdang"			},
			new Object[] {"kajang"			, "kajang"			},
			new Object[] {"semenyih sentral", "semenyih sentral"},
			new Object[] {"gombak"			, "gombak"			},
			new Object[] {"taman melati"	, "taman melati"	},
			new Object[] {"wangsa maju"		, "wangsa maju"		},
			new Object[] {"setiawangsa"		, "setiawangsa"		}
		};
	}
	
	@Test
	@Parameters (method = "getValidStationNameParams")
	public void testValidateStationValid(String stationName, String expectedName) {
		
		// Run Method
		Station resultStation = ri.validateStation(stationName);
		
		// Compare Results
		assertEquals(resultStation.getStationName(), expectedName);
	}
	
	// Invalid Parameters
	private Object getInvalidStationNameParams() {
		return new Object[] {
			new Object[] {null},		// Null Station Name
			new Object[] {anyString()}	// Invalid Station Name
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidStationNameParams")
	public void testValidateStationInvalid(String stationName) {
		
		// Run Method
		ri.validateStation(stationName);
	}


	// Route Info
	// Valid Parameters
	private Object getValidGetRouteDistanceParams() {
		return new Object[] {
			// Random Stations
			new Object[] {"rawang"		, "bandaraya"		, 36.0},
			new Object[] {"taman melati", "gombak"			,  4.0},
			new Object[] {"shah alam"	, "masjid jamek"	, 96.5},
			new Object[] {"sungai buloh", "setiawangsa"		, 38.0},
			new Object[] {"sentul timur", "batu kentonmen"	, 23.0},
			new Object[] {"serdang"		, "semenyih sentral", 16.0},
			new Object[] {"mid valley"	, "kl sentral"		,  5.0},
			new Object[] {"subang jaya"	, "wangsa maju"		, 38.5},
			new Object[] {"bangsar"		, "ampang park"		, 77.5},
			new Object[] {"klcc"		, "kepong sentral"	, 62.0}
		};
	}

	@Test
	@Parameters (method = "getValidGetRouteDistanceParams")
	public void testGetRouteDistanceValidValues(String startStation, String endStation, double expectedResult) {

		// Run Method
		double result = ri.getRouteDistance(startStation, endStation);

		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}

	// Invalid Parameters
	private Object getInvalidGetRouteDistanceParams() {
		return new Object[] {
			// Origin Station
			new Object[] {null			, "klcc"},	// Null Station
			new Object[] {anyString()	, "klcc"},	// Invalid Station
			// End Station
			new Object[] {"klcc"	, null},		// Null Station
			new Object[] {"KLCC"	, anyString()}	// Invalid Station
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidGetRouteDistanceParams")
	public void testGetRouteDistanceInvalidValues(String startStation, String endStation) {
		
		// Run Method
		ri.getRouteDistance(startStation, endStation);
	}
}