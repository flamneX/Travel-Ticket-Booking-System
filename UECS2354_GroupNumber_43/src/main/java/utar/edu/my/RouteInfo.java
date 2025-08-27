package utar.edu.my;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteInfo {
	private List<Station> stationList = new ArrayList<>();
	// Station Names
	private final String[] stationNameList = {"KL Sentral", "Mid Valley", "Subang Jaya", "Shah Alam", "Bangsar", "Kepong Sentral", "Sentul Timur", "Titiwangsa", 
			"Ampang Park", "KLCC", "Masjid Jamek", "Bandaraya", "Batu Kentonmen", "Rawang", "Sungai Buloh", "Serdang", "Kajang", "Semenyih Sentral", 
			"Gombak", "Taman Melati", "Wangsa Maju", "Setiawangsa"};
	// Station Path From
	private final String[] station1List = {"KL Sentral", "KL Sentral", "Subang Jaya", "Bangsar", "KL Sentral", "Sentul Timur", "Titiwangsa", "Ampang Park", 
			"KLCC", "Masjid Jamek", "Bandaraya", "Batu Kentonmen", "Rawang", "Sungai Buloh", "Serdang", "Kajang", 
			"Gombak", "Taman Melati", "Wangsa Maju", "Setiawangsa"};
	// Station Path To
	private final String[] station2List = {"Mid Valley", "Subang Jaya", "Shah Alam", "KL Sentral", "Kepong Sentral", "Titiwangsa", "Ampang Park", "KLCC", 
			"Masjid Jamek", "Bandaraya", "Batu Kentonmen", "Rawang", "Sungai Buloh", "Kepong Sentral", "Kajang", "Semenyih Sentral", 
			"Taman Melati", "Wangsa Maju", "Setiawangsa", "KL Sentral"};
	// Station Path Distance
	private final double[] stationDistance = {5.0, 18.0, 7.0, 2.0, 12.5, 3.5, 4.0, 1.0, 3.0, 1.5, 10.0, 26.0, 12.0, 9.5, 10.0, 6.0, 4.0, 2.0, 4.5, 16.0};
	
	// Setup all Stations, Paths & Distance
	public RouteInfo() {
		for (String stationName : stationNameList) {
			stationList.add(new Station(stationName));
		}
		
		// Add All Station Paths
		for (int i = 0; i < 20; i++) {
			Station stationFrom = null;
			Station stationTo = null;
			for (Station station : stationList) {
				// Get Starting Station
				if (station.getStationName().equals(station1List[i])) {
					stationFrom = station;
				}
				// Get Ending Station
				else if (station.getStationName().equals(station2List[i])) {
					stationTo = station;
				}
				// Set Path for Stations to Run Both Ways
				if (stationFrom != null && stationTo != null) {
					stationFrom.addStation(stationTo, stationDistance[i]);
					stationTo.addStation(stationFrom, stationDistance[i]);
					break;
				}
			}
		}
	}
	

	// Find Start & End Stations
	// Helper Method for Routing Algorithm
	public Double getRouteDistance(String origin, String destination) throws IOException {
		Station originStation = null;
		Station destinationStation = null;
		
		// Search for Start & End Station Details
		for (Station station : stationList) {
			// Start Station
			if (station.getStationName().toUpperCase().equals(origin.toUpperCase())) {
				originStation = station;
			}
			// End Station
			else if (station.getStationName().toUpperCase().equals(destination.toUpperCase())) {
				destinationStation = station;
			}
			// Stop the Loop
			if (originStation != null && destinationStation != null) {
				break;
			}
		}
		
		// Invalid Station
		if (!stationList.contains(originStation) || !stationList.contains(destinationStation)) {
			throw new IOException();
		}
		// Same Station
		else if (originStation.equals(destinationStation)) {
			throw new IOException();
		}
		
		// Start Routing
		return findRoute(null, originStation, destinationStation);
	}
	
	// Calculate Shortest Distance Between Stations
	public Double findRoute(Station previousStation, Station currentStation, Station destination) {
		Double currentDistance = Double.MAX_VALUE;
		List<Double> distances = new ArrayList<>();
		
		// Arrived at End Station
		if (currentStation.equals(destination)) {
			return 0.0;
		}
		// Map Route
		else {
			// List of Next Stations
			HashMap<Station, Double> nextStations = currentStation.getLinkedStations();
			
			// Map Through All Possible Routes
			boolean hasRoute = false;
			for (Map.Entry<Station, Double> nextStation : nextStations.entrySet()) {
				
				// Ensure Path Cannot Go Backwards
				if (!nextStation.getKey().equals(previousStation)) {
					
					// Get Next Distance (-1) if Dead End
					Double nextDistance = findRoute(currentStation, nextStation.getKey(), destination);
					// Valid Route
					if (nextDistance >= 0.0) {
						distances.add(nextStation.getValue() + nextDistance);
						hasRoute = true;
					}
					// Invalid Route
					else {
						distances.add(nextDistance);
					}
				}
			}
			
			// Get Shortest Distance
			for (Double distance : distances) {
				if (distance > 0 && distance < currentDistance) {
					currentDistance = distance;
				}
			}
			
			// Return Shortest Distance
			if (hasRoute) {
				return currentDistance;
			}
			// No Route
			else {
				return -1.0;
			}
		}
	}
}