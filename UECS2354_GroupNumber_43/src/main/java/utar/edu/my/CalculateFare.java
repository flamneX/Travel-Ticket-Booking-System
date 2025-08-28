package utar.edu.my;

import java.io.IOException;
import java.util.List;

public class CalculateFare {
	RouteInfo route = new RouteInfo();
	ApplyDiscountSurcharge discount = new ApplyDiscountSurcharge();
	
	// Calculate Total Distance Traveled
	public Double routeDistance(String startStation, String endStation) throws IOException {
		return route.getRouteDistance(startStation, endStation);
	}
	
	// Calculate Total Fare Based on Distance Traveled
	public Double totalFare(Double distance) throws IOException {
		Double fare = 0.0;
		
		// Invalid Range
		if (distance < 1 || distance > 30) {
			throw new IOException();
		}
		else {
			// Loop till Complete
			do {
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
			} while (distance > 0);
		}
		return fare;
	}
	
	// Calculate Discount Details
	public Double discountedFare(Double fare, Double travelDistance, List<String> passengerType, List<Integer> passengerQuantity, String travelDay, String travelTime) throws IOException {
		Double discountedFare = 0.0;
		
		// Get Day Time Discount Details
		int dayTimeDiscount = discount.dayTimeDiscount(true, travelTime);
		
		// Get Discounted Fare by Passenger Type & Quantity
		if (passengerType.size() != passengerQuantity.size() || passengerType.isEmpty() || passengerQuantity.isEmpty()) {
			throw new IOException();
		}
		else {
			// Passenger Type Loop
			for (int i = 0; i < passengerType.size(); i++) {
				// Passenger Amount Loop
            	for (int j = 0; j < passengerQuantity.get(i); j++) {
            	    Double passengerFare = fare * (discount.passengerDiscount(travelDistance, passengerType.get(i))/100);
				
            	    // Add on Discount for Travel Day & Time
            	    if (dayTimeDiscount == 2) {
            	        discountedFare += passengerFare + 2;
            	    }
            	    else {
            	        discountedFare += passengerFare * (dayTimeDiscount/100);
            	    }
            	}
			}
		}
		return discountedFare;
	}
}
