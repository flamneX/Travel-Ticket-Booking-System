package utar.edu.my;

import java.util.List;

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
	// Status
	private String bookingStatus = "Not Paid";
	private String paymentStatus = "Pending";
	// Classes
	private CalculateFare cf;
	private Payment p;

	// Constructors
	public Booking(CalculateFare cf, Payment p) {
		this.cf = cf;
		this.p = p;
	}
	
	public Booking(IUser booker, String travelDay, String travelTime, String startStation, String endStation, List<String> passengerType, 
			List<Integer> passengerQuantity, String paymentMethod) {
		// Classes
		cf = new CalculateFare();
		p = new Payment();
		// Data
		this.booker = booker;
		this.travelDay = travelDay;
		this.travelTime = travelTime;
		this.startStation = startStation;
		this.endStation = endStation;
		this.passengerType = passengerType;
		this.passengerQuantity = passengerQuantity;
		this.paymentMethod = paymentMethod;
	}
	
	
	// Get Methods
	public String getBookingStatus() {
		return bookingStatus;
	}
	
	public String getPaymentStatus() {
		return paymentStatus;
	}
	
	
	// Set Methods
	public void setTravelDay(String travelDay) {
		this.travelDay = travelDay;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	
	public void setPassengerType(List<String> passengerType) {
		this.passengerType = passengerType;
	}
	
	public void setPassengerQuantity(List<Integer> passengerQuantity) {
		this.passengerQuantity = passengerQuantity;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	

	// Calculate Total Fare For One Person
	public double getTotalFare() {
		cf.calculateTotalFare(startStation, endStation);
		return cf.getTotalFare();
	}

	// Calculate Discounted Fare Amount For All Person
	public double getDiscountedFare() {
		cf.calculateDiscountedFare(travelDay, travelTime, startStation, endStation, passengerType, passengerQuantity);
		return cf.getDiscountedFare();
	}
	
	// Get Details of Discount
	public List<String> getDiscountDetails() {
		List<String> details = cf.getAdjustmentDetails();
		
		if (details == null || details.size() == 0)
			throw new IllegalArgumentException("No Details Found");
		else
			return details;
	}

	// Make Payment
	public void makePayment() {

		// Calculate Payment Amount
		cf.calculatePayment(paymentMethod);
		
		// Make Payment
		p.makePayment(cf.getPaymentAmount());
		
		// Print Receipt On Completion
		paymentStatus = p.getStatus();
		
		if (paymentStatus.equals("Paid"))
			p.emailReceipt();
			bookingStatus = "Confirmed Booking";
	}
}