package utar.edu.my;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class BookingUnitTest {
	// Target Class
	private CalculateFare cfMock;
	private Payment pMock;
	private Booking b;


	// Setup For all Test Classes
	@Before
	public void setupClass() {
		// Setup Class
		cfMock= mock(CalculateFare.class);
		pMock = mock(Payment.class);
		b = new Booking(cfMock, pMock);
	}


	// Get Total Fare
	@Test
	public void testGetTotalFare() {
		
		// Run Method
		b.getTotalFare();
		
		// Verify Method Execution
		verify(cfMock, times(1)).calculateTotalFare(null, null);
		verify(cfMock, times(1)).getTotalFare();
	}


	// Get Discounted Fare
	@Test
	public void testGetDiscountedFare() {
		
		// Run Method
		b.getDiscountedFare();
		
		// Verify Method Execution
		verify(cfMock, times(1)).calculateDiscountedFare(null, null, null, null, null, null);
		verify(cfMock, times(1)).getDiscountedFare();
	}


	// Get Discounted Detail
	// Valid Parameters
	@Test
	public void testGetDiscountDetailValid() {
		
		// Mock Output
		when(cfMock.getAdjustmentDetails()).thenReturn(new ArrayList<String> (Arrays.asList(new String[]{""})));

		// Run Method
		b.getDiscountDetails();
			
		// Verify Method Execution
		verify(cfMock, times(1)).getAdjustmentDetails();
	}
	
	// Invalid Parameters
	@Test (expected = IllegalArgumentException.class)
	public void testGetDiscountDetailInvalid() {
		// Run Method
		b.getDiscountDetails();			// Null Details
	}


	// Make Payment
	@Test
	public void testMakePaymentValid() {
		
		// Mock Status Output
		when(pMock.getStatus()).thenReturn("Paid");
		
		// Run Method
		b.makePayment();
		
		// Verify Method Execution
		verify(cfMock, times(1)).calculatePayment(null);
		verify(cfMock, times(1)).getPaymentAmount();
		verify(pMock, times(1)).makePayment(anyDouble());
		verify(pMock, times(1)).getStatus();
		verify(pMock, times(1)).emailReceipt();
	}
}