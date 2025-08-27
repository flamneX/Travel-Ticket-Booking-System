package utar.edu.my;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		RouteInfo route = new RouteInfo();
		System.out.println(route.getRouteDistance("KLCC", "asd"));
		
		CalculateFare c = new CalculateFare();
		System.out.println(c.distanceFare(25.0));
	}

}
