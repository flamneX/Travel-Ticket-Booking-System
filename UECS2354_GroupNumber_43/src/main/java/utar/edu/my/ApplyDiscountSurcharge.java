package utar.edu.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplyDiscountSurcharge {
	private final List<String> weekday = new ArrayList<>(Arrays.asList("MON", "TUE", "WED", "THU", "FRI"));
	private final List<String> weekend = new ArrayList<>(Arrays.asList("SAT", "SUN"));
	
	// Discount Based on Passenger Type
	public double passengerDiscount(double travelDistance, String passengerType) {
		double discountRate = 1.0;
		
		// Invalid Inputs
		// Null Passenger
		if (passengerType == null)
			throw new IllegalArgumentException("Null Passenger Type");
		// Invalid Travel Distance Range
		else if (travelDistance <= 0 || travelDistance > 30)
			throw new IllegalArgumentException("Invalid Travel Distance");
		
		
		// Providing discount Rate Based on Passenger Type 
		switch (passengerType.toUpperCase()) {
			case "ADULT" -> discountRate = 1.0;
			case "SENIOR" -> discountRate = 0.5;
			case "STUDENT" -> discountRate = 0.7;
			case "CHILD" -> {
				if (travelDistance >= 5) {
					discountRate = 0.5;
				}
				else {
					discountRate = 0.0;
				}
			}
			// Invalid Passenger Type
			default -> throw new IllegalArgumentException("Invalid Passenger Type");
		}
		return discountRate;
	}
	
	// Confirm Whether Travel Day is Weekend or Weekday
	public boolean isWeekend(String travelDay) {

		// Invalid Input
		if (travelDay == null) {
			throw new IllegalArgumentException("Travel Day is Null");
		}
		
		// Convert Standard Name to Short Form
		switch (travelDay.toUpperCase()) {
			case "MONDAY" -> travelDay = "MON";
			case "TUESDAY" -> travelDay = "TUE";
			case "WEDNESDAY" -> travelDay = "WED";
			case "THURSDAY" -> travelDay = "THU";
			case "FRIDAY" -> travelDay = "FRI";
			case "SATURDAY" -> travelDay = "SAT";
			case "SUNDAY" -> travelDay = "SUN";
			default -> travelDay = travelDay.toUpperCase();
		}
		
		// Weekend
		if (weekend.contains(travelDay))
			return true;
		// Weekday
		else if (weekday.contains(travelDay))
			return false;
		// Invalid Travel Day
		else
			throw new IllegalArgumentException("Invalid Date Format");
	}
	
	// Discount Based on Day & Time (24 hour Format)
	public int dayTimeDiscount(boolean weekend, String travelTime) {
		int discountRate = 100;
		int time;
		
		try {
			// Convert Time to Integer
			time = Integer.parseInt(travelTime);
			
			// Invalid Time Range
			if (time < 0 || time > 2399)
				throw new NumberFormatException();
			// Invalid Time Format
			else if (time%100 > 60)
				throw new NumberFormatException();
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid Time Format");
		}
		
		// Night Sur-charge (+RM2)
		if (time >= 2200)
			discountRate = 2;
		// Weekend Discount (-10%)
		else if (weekend)
			discountRate = 90;
		// Weekday Rush Hour Sur-charge (+20%)
		else 
			if ((time >= 630 && time <= 930) || (time >= 1700 && time <= 2000))
				discountRate = 120;
		
		// Return DiscountedAmount
		return discountRate;
	}
}