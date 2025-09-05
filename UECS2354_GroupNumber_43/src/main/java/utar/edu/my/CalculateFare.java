package utar.edu.my;

import java.util.ArrayList;
import java.util.List;

public class CalculateFare {
	// Integrated Classes
	private RouteInfo ri;
	private FareAdjustment fa;
	// Data Fields
	private double distance;
	private double distanceFare;
	private double totalFare;
	private double paymentFare;


	// Constructors
	public CalculateFare() {
		ri = new RouteInfo();
		fa = new FareAdjustment();
	}
	
	public CalculateFare(RouteInfo ri, FareAdjustment fa) {
		this.ri = ri;
		this.fa = fa;
	}


	// Get Methods
	public double getDistanceTravelled() {
		return distance;
	}
	
	public double getDistanceFare() {
		return distanceFare;
	}


	// Other Methods
	// Calculate Total Fare Based on Distance Traveled
	public void calculateDistanceFare(String startStation, String endStation) {
		Double fare = 0.0;
		
		// Get Distance Traveled
		distance = ri.getRouteDistance(startStation, endStation);
		
		// Invalid Distance Range
		if (distance < 1 || distance > 30)
			throw new IllegalArgumentException("Invalid Distance Value");
		// Valid Distance
		else {
			// Calculate Fare per Distance
			while (distance > 0) {
				if (distance > 20) {
					fare += (distance - 20) * 20;
					distance = 20.0;
				}
				else if (distance > 15) {
					fare += (distance - 15) * 15;
					distance = 15.0;
				}
				else if (distance > 10) {
					fare += (distance - 10) * 10;
					distance = 10.0;
				}
				else if (distance > 5) {
					fare += (distance - 5) * 5;
					distance = 5.0;
				}
				else {
					fare += distance * 2;
					distance = 0.0;
				}
			}
		}
		distanceFare = fare;
	}


	// Calculate Fare After Discount + Passenger
	public void getTotalFare(String travelDay, String travelTime, String startStation, String endStation, List<String> passengerType, List<Integer> passengerQuantity) {
		double totalFare = 0;
		
		// Calculate Distance Fare for Distance Traveled
		calculateDistanceFare(startStation, endStation);
		
		// Null Passenger Type or Passenger Quantity
		if (passengerType == null || passengerQuantity == null)
			throw new IllegalArgumentException("Passenger Type & Quantity Cannot Be Null");
		// Empty Passenger Type or Passenger Quantity
		else if (passengerType.size() == 0 || passengerQuantity.size() == 0) {
			throw new IllegalArgumentException("Passenger Type & Quantity Cannot Be Empty");
		}
		// Passenger Type & Passenger Quantity Are of Different Lengths
		else if (passengerType.size() != passengerQuantity.size())
			throw new IllegalArgumentException("Passenger Quantity Does Not Match Passenger Type");

		
		// Loop through Passengers
		for (int i = 0; i < passengerType.size(); i++) {
			double fare = getDistanceFare();
			
			// Calculate Fare By Passenger Type
			double passengerDiscount = fa.passengerAdjustment(passengerType.get(i));
			fare *= passengerDiscount;
			
			// Calculate Fare By Day & Time
			double dayTimeDiscount = fa.dayTimeAdjustment(travelDay, travelTime);
			if (dayTimeDiscount == 2)
				fare += dayTimeDiscount;
			else
				fare *= dayTimeDiscount/100;
			
			// Multiply Fare By Passenger Amount
			fare *= passengerQuantity.get(i);
			
			// Add to Total Fare
			totalFare += fare;
		}

		this.totalFare = totalFare;
	}

}