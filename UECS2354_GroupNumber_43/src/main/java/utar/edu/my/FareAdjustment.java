package utar.edu.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FareAdjustment {
	private double travelDistance = 0;
	
	// Set Methods
	public void setTravelDistance(double travelDistance) {
		this.travelDistance = travelDistance;
	}
	
	// Passenger Type
	// Validate Passenger Type & Return Formated Passenger Type
	public String validatePassengerType(String passengerType) {
		// Valid Passenger Types
		List<String> validPassengers = new ArrayList<> (Arrays.asList("adult", "senior citizen"
				, "student", "child"));
		
		// Null Passenger Type
		if (passengerType == null) {
			throw new IllegalArgumentException("Passenger Type Cannot Be Null");
		}
		
		// Format Passenger Type
		passengerType = passengerType.trim().toLowerCase();
		
		
		// Invalid Passenger Type
		if (!validPassengers.contains(passengerType))
			throw new IllegalArgumentException("Invalid Passenger Type");
		// Valid Passenger Type
		else
			return passengerType;
	}
	
	// Fare Adjustment Based on Passenger Type
	public double passengerAdjustment(String passengerType) {
		double adjustment = 1.0;
				
		// Invalid Travel Distance Range
		if (travelDistance <= 0 || travelDistance > 30)
			throw new IllegalArgumentException("Invalid Travel Distance");
	
		// Providing discount Rate Based on Passenger Type 
		switch (validatePassengerType(passengerType)) {
			case "adult" -> adjustment = 1.0;
			case "senior citizen" -> adjustment = 0.5;
			case "student" -> adjustment = 0.7;
			case "child" -> {
				if (travelDistance >= 5)
					adjustment = 0.5;
				else
					adjustment = 0.0;
			}
		}
		return adjustment;
	}


	// Travel Day
	// Validate Travel Day & Return Boolean isWeekend
	public boolean isWeekend(String travelDay) {
		// Valid Days of Week
		List<String> daysOfWeek = new ArrayList<> (Arrays.asList("monday", "tuesday", "wednesday", 
				"thursday", "friday", "saturday", "sunday"));

		// Null Travel Day
		if (travelDay == null)
			throw new IllegalArgumentException("Travel Day Cannot Be Null");
		
		// Format Travel Day
		travelDay = travelDay.trim().toLowerCase();
		
		// Invalid Travel Day
		if (!daysOfWeek.contains(travelDay))
			throw new IllegalArgumentException("Invalid Travel Day");
		// Valid Travel Day
		else
			// Weekends
			if (travelDay == "saturday" || travelDay == "sunday")
				return true;
			// Weekdays
			else
				return false;
	}
	
	// Validate Travel Time & Return Time as Integer
	public int validateTravelTime(String travelTime) {
		int time;
		
		// Null Travel Time
		if (travelTime == null)
			throw new IllegalArgumentException("Travel Time Cannot Be Null");
		
		try {
			// Convert Time to Integer
			time = Integer.parseInt(travelTime);
			
			// Invalid Time Range
			if (time < 0 || time > 2359)
				throw new NumberFormatException();
			// Invalid Time Format
			else if (time%100 >= 60)
				throw new NumberFormatException();
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid Time Format");
		}
		return time;
	}
	
	// Payment Adjustment Based on Day & Time (24 hour Format)
	public int dayTimeAdjustment(String travelDay, String travelTime) {
		// Normal Discount Rate
		int adjustment = 100;
		
		// Validate Inputs
		boolean weekend = isWeekend(travelDay);
		int time = validateTravelTime(travelTime);
		
		// Night Sur-charge (+RM2)
		if (time >= 2200)
			adjustment = 2;
		// Weekend Discount (-10%)
		else if (weekend)
			adjustment = 90;
		// Weekday Rush Hour Sur-charge (+20%)
		else 
			if ((time >= 630 && time <= 930) || (time >= 1700 && time <= 2000))
				adjustment = 120;
		
		// Return Discounted Rate
		return adjustment;
	}
	

	// Payment Method
	// Validate Payment Method
	public String validatePaymentMethod(String paymentMethod) {
		// Valid Payment Methods
		List<String> validPaymentMethods = new ArrayList<> (Arrays.asList("e-wallet", "credit card", 
				"online banking"));
				
		// Null Payment Method
		if (paymentMethod == null) {
			throw new IllegalArgumentException("Payment Method Cannot Be Null");
		}

		// Format Payment Method
		paymentMethod = paymentMethod.trim().toLowerCase();
		
		// Invalid Payment Method
		if (!validPaymentMethods.contains(paymentMethod))
			throw new IllegalArgumentException("Invalid Payment Method");
		// Valid Payment Method
		else
			return paymentMethod;
	}
	
	// Fare Adjustment Based on Payment Method
	public double paymentMethodAdjustment(String paymentMethod) {
		double adjustment = 100;
		
		// Payment Method Discount/Sur-charge
		switch (validatePaymentMethod(paymentMethod)) {
			case "e-wallet" -> adjustment = 1.0;
			case "credit card" -> adjustment = 1.05;
			case "online banking" -> adjustment = 0.95;
		}
		
		// Final Fare Amount
		return adjustment;
	}
}