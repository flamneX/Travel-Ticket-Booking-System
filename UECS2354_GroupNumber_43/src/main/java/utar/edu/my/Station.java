package utar.edu.my;

import java.util.HashMap;

public class Station {
	private String stationName;
	private HashMap<Station, Double> linkedStations = new HashMap<>();

	public Station(String stationName) {
		this.stationName = stationName;
	}
	
	// Get Functions
	public String getStationName() {
		return stationName;
	}

	public HashMap<Station, Double> getLinkedStations() {
		return linkedStations;
	}
	
	// Add Next Linking Stations to Current Station
	public void addStation(Station nextStation, double distance) {
		linkedStations.put(nextStation, distance);
	}
	
	// Base Functions
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		Station station = (Station) obj;
		return stationName.equals(station.getStationName());
	}
	
	@Override
	public int hashCode() {
		return stationName != null ? stationName.hashCode() : 0;
	}
}