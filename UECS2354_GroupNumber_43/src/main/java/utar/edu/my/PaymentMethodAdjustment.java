package utar.edu.my;

import java.util.HashMap;

public class PaymentMethodAdjustment {
	private HashMap<String, Double> adjustmentRateMap = new HashMap<>();
	private String currentPaymentMethod;

	// Setup Adjustment Rates
	public PaymentMethodAdjustment() {
		adjustmentRateMap.put("e-Wallet", 0.0);
		adjustmentRateMap.put("Credit Card", 1.05);
		adjustmentRateMap.put("Online Banking", 0.95);
	}
	
	public double paymentAdjustment(String paymentMethod) {
		
		return 1.0;
	}

}
