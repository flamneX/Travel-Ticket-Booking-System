package utar.edu.my;

import java.util.ArrayList;
import java.util.List;

public class GuestFile {
	private FileFunctionality ff;
	
	// Constructors
	public GuestFile() {
		ff = new FileFunctionality();
	}
	
	public GuestFile(FileFunctionality ff) {
		this.ff = ff;
	}
	
	
	// Read Guest Data from File
	public List<Guest> readGuestFromFile(String fileName) {
		List<Guest> guestList = new ArrayList<> ();
			
		// Retrieve Guest Details from File
		String[] guestArray = ff.readFromFile(fileName);
			
		// Construct Guest ArrayList
		for (String guestLine : guestArray) {
			String[] guestDetail = guestLine.split(";");
				
			// Invalid Guest File Format
			if (guestDetail.length != 3)
				throw new IllegalArgumentException("Invalid Guest Detail Format");
			// Convert String to Guest Object
			else
				guestList.add(new Guest(guestDetail[0], guestDetail[1], guestDetail[2]));
		}
					
		return guestList;
	}
	
	
	// Write New Guest to File
	public void writeGuestToFile(Guest[] guestArray, String fileName) {
		List<String> guestStringList = new ArrayList<>();
			
		// Null Guest Array
		if (guestArray == null)
			throw new IllegalArgumentException("Guest List Cannot Be Null");
		
		// Convert Guest Class Array to String List
		for (Guest guest : guestArray) {
			String guestString = guest.toString();
			guestStringList.add(guestString);
		}
			
		// Convert Array List to Array
		String[] guestStringArray = guestStringList.toArray(new String[guestStringList.size()]);
			
		// Write to File
		ff.writeToFile(guestStringArray, fileName);
	}
}