package utar.edu.my;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class ApplyDiscountSurcharge {
	private List<String> weekday = new ArrayList<>(Arrays.asList("MON", "TUE", "WED", "THU", "FRI"));
	private List<String> weekend = new ArrayList<>(Arrays.asList("SAT", "SUN"));
	
	// Discount Based on Passenger Type
	public int passengerDiscount(Double travelDistance, String passengerType) throws IOException {
		int discountRate = 100;
		
		
		// Providing discount Rate Based on Passenger Type 
		switch (passengerType.toUpperCase()) {
			case "ADULT" -> discountRate = 100;
			case "SENIOR" -> discountRate = 50;
			case "SENIOR CITIZEN" -> discountRate = 50;
			case "SENIORCITIZEN" -> discountRate = 50;
			case "STUDENT" -> discountRate = 70;
			case "CHILD" -> {
				if (travelDistance > 5) {
					discountRate = 50;
				}
				else {
					discountRate = 0;
				}
			}
			// Input Error
			default -> throw new IOException();
		}
		return discountRate;
	}
	
	// Discount Based on Day & Time (24 hour Format)
	public int dayTimeDiscount(String travelDay, String travelTime) throws IOException {
		int discountRate = 100;
		int time;
		
		// Convert Day to Short Form
		switch (travelDay.toUpperCase()) {
			case "MONDAY" -> travelDay = "MON";
			case "TUESDAY" -> travelDay = "TUE";
			case "WEDNESDAY" -> travelDay = "WED";
			case "THURSDAY" -> travelDay = "THU";
			case "FRIDAY" -> travelDay = "FRI";
			case "SATURDAY" -> travelDay = "SAT";
			case "SUNDAY" -> travelDay = "SUN";
		}
		
		// Convert Time to Integer
		try {
			time = Integer.parseInt(travelTime);
		}
		catch (NumberFormatException e) {
			throw new IOException();
		}
		
		// Night Surcharge (+RM2)
		if (time > 2200) {
			discountRate = 2;
		}
		// Weekend Discount (-10%)
		else if (weekend.contains(travelDay)) {
			discountRate = 90;
		}
		// Weekday Rush Hour Surcharge (+20%)
		else if (weekday.contains(travelDay)) {
			if ((time > 630 && time < 930) || (time > 1700 && time < 2000)) {
				discountRate = 120;
			}
		}
		// Error Input
		else {
			throw new IOException();
		}
		
		// Return DiscountedAmount
		return discountRate;
	}
}