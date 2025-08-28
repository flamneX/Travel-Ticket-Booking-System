package utar.edu.my;

public class PaymentMethodAdjustment {
	
	// Discount Rates Based on Payment Method
	public double paymentAdjustment(PaymentMethod paymentMethod) {
		double discountRate = 100;
		
		// Null Payment Method
		if (paymentMethod == null) {
			throw new IllegalArgumentException("Null Payment Method");
		}
		
		// Payment Method Discount/Sur-charge
		switch (paymentMethod) {
			case EWALLET -> discountRate = 1.0;
			case CREDITCARD -> discountRate = 1.05;
			case ONLINEBANKING -> discountRate = 0.95;
		}
		
		// Final Fare Amount
		return discountRate;
	}
}

enum PaymentMethod {
	EWALLET, 
	CREDITCARD, 
	ONLINEBANKING
}