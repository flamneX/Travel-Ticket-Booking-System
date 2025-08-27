package utar.edu.my;

public class ApplyDiscountSurcharge {
	private Double travelDistance;
	private String passengerType;

	public ApplyDiscountSurcharge(String passengerType, String travelDay, String travelTime) {
		this.passengerType = passengerType;
	
	}
	
	public double discountSurcharge() {
		return 1.0;
	}
	
	public double passengerDiscount() {
		double discountRate = 1.0;
		
		// Providing discount Rate Based on Passenger Type 
		switch (passengerType.toUpperCase()) {
			case "ADULT" -> discountRate = 1.0;
			case "SENIOR" -> discountRate = 0.5;
			case "STUDENT" -> discountRate = 0.7;
			case "CHILD" -> {
				if (travelDistance > 5) {
					discountRate = 0.5;
				}
				else {
					discountRate = 0.0;
				}
			}
		}
		return discountRate;
	}
}
