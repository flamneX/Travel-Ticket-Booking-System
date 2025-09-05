package utar.edu.my;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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


	// Calculate Distance Fare
	// Valid Parameters - Equivalent Partitioning (EP)
	private Object getValidCalculateDistanceFareParamsEP() {
		return new Object[] {
			new Object[] { 3.0,   6.0},	//  0 < x <=  5
			new Object[] { 8.0,  25.0},	//  5 < x <= 10
			new Object[] {13.0,  65.0},	// 10 < x <= 15
			new Object[] {18.0, 130.0},	// 15 < x <= 20
			new Object[] {25.0, 260.0},	// 20 < x <= 30
		};
	}
	
	@Test
	@Parameters (method = "getValidCalculateDistanceFareParamsEP")
	public void testCalculateDistanceFareValidEP(double distance, double expectedResult) {
		
		// Mock Route Info Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		
		// Run Method
		cf.calculateDistanceFare(anyString(), anyString());
		double result = cf.getDistanceFare();
		
		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Values - Equivalent Partitioning (EP)
	private Object getInvalidCalculateDistanceFareParamsEP() {
		return new Object[] {
			new Object[] {-5.0},	// Invalid Distance Range
			new Object[] {35.0},
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculateDistanceFareParamsEP")
	public void testCalculateDistanceFareInvalidEP(double distance) {

		// Mock Route Info Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		
		// Run Method
		cf.calculateDistanceFare(anyString(), anyString());
	}

	// Valid Parameters - Boundary Value Analysis (BVA)
	private Object getValidCalculateDistanceFareParamsBVA() {
		return new Object[] {
			new Object[] { 1.0,   2.0},	//  0 < x <=  5
			new Object[] { 5.0,  10.0},
			new Object[] { 6.0,  15.0},	//  5 < x <= 10
			new Object[] {10.0,  35.0},
			new Object[] {11.0,  45.0},	// 10 < x <= 15
			new Object[] {15.0,  85.0},
			new Object[] {16.0, 100.0},	// 15 < x <= 20
			new Object[] {20.0, 160.0},
			new Object[] {21.0, 180.0},	// 20 < x <= 30
			new Object[] {30.0, 360.0},
		};
	}
	
	@Test
	@Parameters (method = "getValidCalculateDistanceFareParamsBVA")
	public void testCalculateDistanceFareValidBVA(double distance, double expectedResult) {
		
		// Mock Route Info Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		
		// Run Method
		cf.calculateDistanceFare(anyString(), anyString());
		double result = cf.getDistanceFare();
		
		// Compare Result
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters - Boundary Value Analysis (BVA)
	private Object getInvalidCalculateDistanceFareParamsBVA() {
		return new Object[] {
			new Object[] {0.5},		// 0 < x <= 30 (Valid)
			new Object[] {30.5}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidCalculateDistanceFareParamsBVA")
	public void testCalculateDistanceFareInvalidBVA(double distance) {
		
		// Mock Route Info Output
		when(riMock.getRouteDistance(anyString(), anyString())).thenReturn(distance);
		
		// Run Method
		cf.calculateDistanceFare(anyString(), anyString());
	}
	
	
	// Calculate Total Fare
	// Valid Parameters
	public void testCalculateTotalFare(double passengerDiscount, double disCount) {
		
		when(faMock.passengerAdjustment(anyString())).thenReturn(passengerDiscount);
	}
}