package utar.edu.my;

import java.io.IOException;

public class PaymentMethodAdjustment {
	
	// Discount Rates Based on Payment Method
	public Double paymentAdjustment(double fare, String paymentMethod) throws IOException {
		Double discountRate;
		
		// Adjustment for Other Payment Method Names
		switch (paymentMethod.toUpperCase()) {
			case "E-WALLET" -> paymentMethod = "EWALLET";
			case "CREDIT CARD" -> paymentMethod = "CREDIT";
			case "CREDIRCARD" -> paymentMethod = "CREDIT";
			case "ONLINE BANKING" -> paymentMethod = "BANKING";
			case "ONLINEBANKING" -> paymentMethod = "BANKING";
		}
		
		// Payment Method Discount/Surcharge
		switch (paymentMethod.toUpperCase()) {
			case "EWALLET" -> discountRate = 1.0;
			case "CREDIT" -> discountRate = 1.05;
			case "BANKING" -> discountRate = 0.95;
			default -> throw new IOException();
		}
		
		// Final Fare Amount
		return fare * discountRate;
	}
}
