package utar.edu.my;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

public class FileFunctionality {
	
	// Write Array of String into File
	public void writeToFile(String[] inputArray, String fileName) {
		
		// Null File Name
		if (fileName == null)
			throw new IllegalArgumentException("File Name Cannot Be Null/Empty");
		// Null Input Array
		else if (inputArray == null)
			throw new IllegalArgumentException("Input Array Cannot Be Null");
		
		// Set File Name to File Class
		File targetFile = new File("Database\\" + fileName);
		
		// Create Buffered Reader
		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(targetFile))) {
			
			// Write To File Line by Line
			for (String inputLine : inputArray) {
				fileWriter.write(inputLine);
				fileWriter.newLine();
			}
		}
		// Target File Not Found
		catch (FileNotFoundException fnf) {
			throw new IllegalArgumentException("File \"" + fileName + "\" Does Not Exist In Database");
		}
		// Error Writing to File
		catch (IOException io) {
			throw new IllegalArgumentException("Error Writing To :" + fileName);
		}
	}
	
	// Read Data from Specified File Path and Parse to Array of String
	public String[] readFromFile(String fileName) {
		List<String> outputList = new ArrayList<>();
		
		//Invalid File Name
		if (fileName == null)
			throw new IllegalArgumentException("File Name Cannot Be Null/Empty");
		
		// Set File Name to File Class
		File targetFile = new File("Database\\" + fileName);
				
		// Create Buffered Reader
		try (BufferedReader fileReader = new BufferedReader(new FileReader(targetFile))) {
			
			// Read Data Into String
			String fileLine;
			while ((fileLine = fileReader.readLine()) != null) {
				outputList.add(fileLine);
			}
		}
		// Target File Not Found
		catch (FileNotFoundException fnf) {
			throw new IllegalArgumentException("File \"" + fileName + "\" Does Not Exist In Database");
		}
		// Error Reading from File
		catch (IOException io) {
			throw new IllegalArgumentException("Error Reading From : " + fileName);
		}
		
		// Return ArrayList as Array
		return outputList.toArray(new String[outputList.size()]); 
	}
}