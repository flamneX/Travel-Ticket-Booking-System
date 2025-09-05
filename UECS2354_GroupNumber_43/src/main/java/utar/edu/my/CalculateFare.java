package utar.edu.my;

import java.util.ArrayList;
import java.util.List;

public class CalculateFare {
	// Integrated Classes
	private RouteInfo ri;
	private FareAdjustment fa;
	// Data Fields
	private double distance;
	private double totalFare;
	private double discountedFare;
	private double paymentAmount;
	private List<String> adjustmentDetails;


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
	public double getDistance() {
		return distance;
	}
	
	public double getTotalFare() {
		return totalFare;
	}
	
	public double getDiscountedFare() {
		return discountedFare;
	}
	
	public double getPaymentAmount() {
		return paymentAmount;
	}
	
	public List<String> getAdjustmentDetails() {
		return adjustmentDetails;
	}
	
	// Set Methods
	public void setDiscountedFare(double discountedFare) {
		this.discountedFare = discountedFare;
	}
	
	// Other Methods
	// Calculate Total Fare Based on Distance Traveled
	public void calculateTotalFare(String startStation, String endStation) {
		Double fare = 0.0;
		
		// Get Distance Traveled
		distance = ri.getRouteDistance(startStation, endStation);
		fa.setTravelDistance(distance);
		
		// Invalid Distance Range
		if (distance < 1)
			throw new IllegalArgumentException("No Routes Found");
		else if (distance > 30)
			throw new IllegalArgumentException("Distance Exceeds Limit");
		// Valid Distance
		else {
			// Calculate Fare for Distance
			if (distance > 20)
				fare += 20;
			else if (distance > 15)
				fare += 15;
			else if (distance > 10)
				fare += 10;
			else if (distance > 5)
				fare += 5;
			else
				fare += 2;
		}
		totalFare = fare;
	}


	// Calculate Fare After Discount + Passenger
	public void calculateDiscountedFare(String travelDay, String travelTime, String startStation, String endStation, List<String> passengerType, List<Integer> passengerQuantity) {
		double sumOfFare = 0;
		List<String> detailList = new ArrayList<> ();
		
		// Calculate Distance Fare for Distance Traveled
		calculateTotalFare(startStation, endStation);
		
		// Null or Empty Passenger Type
		if (passengerType == null || passengerType.size() == 0)
			throw new IllegalArgumentException("Passenger Type Cannot Be Null or Empty");
		// Null or Empty Passenger Quantity
		else if (passengerQuantity == null || passengerQuantity.size() == 0) {
			throw new IllegalArgumentException("Passenger Quantity Cannot Be Null or Empty");
		}
		// Passenger Type & Passenger Quantity Are of Different Lengths
		else if (passengerType.size() != passengerQuantity.size())
			throw new IllegalArgumentException("Passenger Quantity Does Not Match Passenger Type");

		
		// Loop through Passengers
		for (int i = 0; i < passengerType.size(); i++) {
			double fare = totalFare;
			String detail = new String();
			
			// Calculate Fare By Passenger Type
			double passengerDiscount = fa.passengerAdjustment(passengerType.get(i));
			fare *= passengerDiscount;
			detail += "Passenger Adjustment : " + ((1.0 - passengerDiscount) * 100) + " %\n";
			
			// Calculate Fare By Day & Time
			double dayTimeDiscount = fa.dayTimeAdjustment(travelDay, travelTime);
			if (dayTimeDiscount == 2) {
				fare += dayTimeDiscount;
				detail += "Day Time Adjustment  : + RM 2.00\n";
			}
			else {
				fare *= dayTimeDiscount/100;
				detail += "Day Time Adjustment  : " + (double) (dayTimeDiscount - 100) + " %\n";
			}
			
			// Multiply Fare By Passenger Amount
			fare *= passengerQuantity.get(i);
			detail += "Passenger Amount     : " + passengerQuantity.get(i) + "\n";
			
			// Add to Total Fare
			sumOfFare += fare;
			detailList.add(detail);
		}

		discountedFare = sumOfFare;
		adjustmentDetails = detailList;
	}


	// Calculate Payment For Fare Based On Payment Method
	public void calculatePayment(String paymentMethod) {
		
		// Null Fare Amount
		if (discountedFare <= 0)
			throw new IllegalArgumentException("Fare Has Not Been Calculated");
		
		// Calculate Payment By Payment Method
		double paymentAdjustment = fa.paymentMethodAdjustment(paymentMethod);
		paymentAmount = discountedFare * paymentAdjustment;
	}
}