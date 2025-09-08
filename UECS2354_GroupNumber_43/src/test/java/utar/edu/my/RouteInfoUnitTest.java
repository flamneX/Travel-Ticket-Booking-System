package utar.edu.my;

import static org.junit.Assert.assertEquals;

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
			new Object[] {"INVALID"}	// Invalid Station Name
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
			new Object[] {"kl sentral"		, "mid valley"		,  5.0},
			new Object[] {"kl sentral"		, "subang jaya"		, 18.0},
			new Object[] {"subang jaya"		, "shah alam"		,  7.0},
			new Object[] {"bangsar"			, "kl sentral"		,  2.0},
			new Object[] {"kl sentral"		, "kepong sentral"	, 12.5},
			new Object[] {"sentul timur"	, "titiwangsa"		,  3.5},
			new Object[] {"titiwangsa"		, "ampang park"		,  4.0},
			new Object[] {"ampang park"		, "klcc"			,  1.0},
			new Object[] {"klcc"			, "masjid jamek"	,  3.0},
			new Object[] {"masjid jamek"	, "bandaraya"		,  1.5},
			new Object[] {"bandaraya"		, "batu kentonmen"	, 10.0},
			new Object[] {"batu kentonmen"	, "rawang"			, 26.0},
			new Object[] {"rawang"			, "sungai buloh"	, 12.0},
			new Object[] {"sungai buloh"	, "kepong sentral"	,  9.5},
			new Object[] {"serdang"			, "kajang"			, 10.0},
			new Object[] {"kajang"			, "semenyih sentral",  6.0},
			new Object[] {"gombak"			, "taman melati"	,  4.0},
			new Object[] {"taman melati"	, "wangsa maju"		,  2.0},
			new Object[] {"wangsa maju"		, "setiawangsa"		,  4.5},
			new Object[] {"setiawangsa"		, "kl sentral"		, 16.0},
		};
	}

	@Test
	@Parameters (method = "getValidGetRouteDistanceParams")
	public void testGetRouteDistanceValid(String startStation, String endStation, double expectedResult) {

		// Run Method
		double result = ri.getRouteDistance(startStation, endStation);

		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}

	// Invalid Parameters
	private Object getInvalidGetRouteDistanceParams() {
		return new Object[] {
			// Origin Station
			new Object[] {null		, "klcc"},			// Null Station
			new Object[] {"INVALID"	, "klcc"},			// Invalid Station
			// End Station
			new Object[] {"klcc"	, null},			// Null Station
			new Object[] {"klcc"	, "INVALID"},		// Invalid Station
			// Same Station
			new Object[] {"klcc"	, "klcc"},			// Same Station
			// Invalid Route
			new Object[] {"kajang"	, "taman melati"}	// Invalid Route
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidGetRouteDistanceParams")
	public void testGetRouteDistanceInvalid(String startStation, String endStation) {
		
		// Run Method
		ri.getRouteDistance(startStation, endStation);
	}
}