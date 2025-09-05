package utar.edu.my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteInfo {
	private List<Station> stationList = new ArrayList<>();
	// Station Names
	private final String[] stationNameList = {"kl sentral", "mid valley", "subang jaya", "shah alam", "bangsar", "kepong sentral", "sentul timur", "titiwangsa", 
			"ampang park", "klcc", "masjid jamek", "bandaraya", "batu kentonmen", "rawang", "sungai buloh", "serdang", "kajang", "semenyih sentral", 
			"gombak", "taman melati", "wangsa maju", "setiawangsa"};
	// Station Path From
	private final String[] station1List = {"kl sentral", "kl sentral", "subang jaya", "bangsar", "kl sentral", "sentul timur", "titiwangsa", "ampang park", 
			"klcc", "masjid jamek", "bandaraya", "batu kentonmen", "rawang", "sungai buloh", "serdang", "kajang", 
			"gombak", "taman melati", "wangsa maju", "setiawangsa"};
	// Station Path To
	private final String[] station2List = {"mid valley", "subang jaya", "shah alam", "kl sentral", "kepong sentral", "titiwangsa", "ampang park", "klcc", 
			"masjid jamek", "bandaraya", "batu kentonmen", "rawang", "sungai buloh", "kepong sentral", "kajang", "semenyih sentral", 
			"taman melati", "wangsa maju", "setiawangsa", "kl sentral"};
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


	// Validate Station Name & Return Station Object
	public Station validateStation(String stationName) {
		
		// Null Station Name
		if (stationName == null)
			throw new IllegalArgumentException("Station Name Cannot Be Null");
		
		// Format Station Name
		stationName = stationName.trim().toLowerCase();
		
		// Search For Station
		for (Station station : stationList) {
			// Found Station
			if (station.getStationName().equals(stationName))
				return station;
		}
		// Station Not Found
		throw new IllegalArgumentException("No Stations Found");
		
	}
	

	// Find Start & End Stations
	// Helper Method for Routing Algorithm
	public Double getRouteDistance(String startStation, String endStation) {
		double distance;
		
		// Find Stations
		Station originStation = validateStation(startStation);
		Station destinationStation = validateStation(endStation);

		// Same Station
		if (originStation.equals(destinationStation))
			throw new IllegalArgumentException("Same Start & End Station");
		
		// Start Routing
		distance = findRoute(null, originStation, destinationStation);
		
		// No Routes Found
		if (distance <= 0)
			throw new IllegalArgumentException("No Routes Found");
		// Found Valid Route
		else
			return distance;
	}


	// Calculate Shortest Distance Between Stations
	private Double findRoute(Station previousStation, Station currentStation, Station destination) {
		Double currentDistance = Double.MAX_VALUE;
		List<Double> distances = new ArrayList<>();
		
		// Check If Arrived at End Station
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