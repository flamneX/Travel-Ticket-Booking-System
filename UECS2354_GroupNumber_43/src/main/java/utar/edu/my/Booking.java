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
	private CalculateFare cf = new CalculateFare();;
	private Payment p = new Payment();

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
	}
	
	
	// Get Methods
	public String getBookingStatus() {
		return bookingStatus;
	}
	
	public String getPaymentStatus() {
		return paymentStatus;
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
			bookingStatus = "Comfirmed Booking";
	}
}