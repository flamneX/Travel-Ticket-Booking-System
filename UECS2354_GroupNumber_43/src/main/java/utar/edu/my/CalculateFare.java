package utar.edu.my;

import java.util.HashMap;

public class CalculateFare {
	
	public CalculateFare() {
		
	}
	
	
	// Calculate Discount Based on Distance Traveled
	public Double distanceFare(Double distance) {
		Double fare = 0.0;
		
		if (distance < 0) {
			
		}
		else if (distance > 30) {
			
		}
		else {
			do {
				if (distance > 20) {
					fare += (distance - 20) * 20;
					distance = 20.0;
				}
				else if (distance > 15) {
					fare += (distance - 15) * 15;
					distance = 15.0;
				}
				else if (distance > 10) {
					fare += (distance - 10) * 10;
					distance = 10.0;
				}
				else if (distance > 5) {
					fare += (distance - 5) * 5;
					distance = 5.0;
				}
				else {
					fare += distance * 2;
					distance = 0.0;
				}
			} while (distance > 0);
		}
		return fare;
	}

}
