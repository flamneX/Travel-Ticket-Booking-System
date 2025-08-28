package utar.edu.my;

import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class PaymentMethodAdjustmentTest {
	// Initialize Payment Method Class
	PaymentMethodAdjustment pma = new PaymentMethodAdjustment();
	
	// Payment Adjustment
	// Valid Parameters
	private Object getValidPaymentAdjustmentParams() {
		return new Object[] {
				new Object[] {PaymentMethod.EWALLET, 1.0},
				new Object[] {PaymentMethod.CREDITCARD, 1.05},
				new Object[] {PaymentMethod.ONLINEBANKING, 0.95},
		};
	}
	
	@Test
	@Parameters (method = "getValidPaymentAdjustmentParams")
	public void testPaymentAdjustment(PaymentMethod paymentMethod, double expectedResult) {
		double result = pma.paymentAdjustment(paymentMethod);
		assertEquals(result, expectedResult, 0.01);
	}
	
	// Invalid Parameters
	private Object getInvalidPaymentAdjustmentParams() {
		return new Object[] {
			new Object[] {null}
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters (method = "getInvalidPaymentAdjustmentParams")
	public void testPaymentAdjustment(PaymentMethod paymentMethod) {
		pma.paymentAdjustment(paymentMethod);
	}
}
