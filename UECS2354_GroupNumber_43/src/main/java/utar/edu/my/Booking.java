package utar.edu.my;

import java.util.List;
import java.io.IOException;

public class Booking {
	private IUser booker;
	private String travelDay;
	private String travelTime;
	private String startStation;
	private String endStation;
	private List<String> passengerType;
	private List<Integer> passengerQuantity;
	private String paymentMethod;
	
	// Calculate Method
	CalculateFare calculate = new CalculateFare();
	
	public Booking(IUser booker, String travelDay, String travelTime, String startStation, String endStation, List<String> passengerType, 
			List<Integer> passengerQuantity, String paymentMethod) {
		this.booker = booker;
		this.travelDay = travelDay;
		this.travelTime = travelTime;
		this.startStation = startStation;
		this.endStation = endStation;
		this.passengerType = passengerType;
		this.passengerQuantity = passengerQuantity;
		this.paymentMethod = paymentMethod;
	}
	
	// Get Total Fare by Distance
	public Double getTotalFare(Double travelDistance) throws IOException {
		return calculate.totalFare(travelDistance);
	}
	
	// Calculate Discounted Fare & Total Amount by Passenger
	public Double getDiscountedFare(Double totalFare, Double travelDistance) throws IOException {
		return calculate.discountedFare(totalFare, travelDistance, passengerType, passengerQuantity, travelDay, travelTime);
	}
}
