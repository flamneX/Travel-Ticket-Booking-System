package utar.edu.my;

import java.util.List;
import java.io.IOException;

public class Booking {
	// Booking Data
	private IUser booker;
	private String travelDay;
	private String travelTime;
	private String startStation;
	private String endStation;
	private List<String> passengerType;
	private List<Integer> passengerQuantity;
	private String paymentMethod;
	// Calculate Method
	private CalculateFare cf;

	// Constructors
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
		this.cf = new CalculateFare(); 
	}
}