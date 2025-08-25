package main.java.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class readUser {
    List<user> userList = readFile();

    // Database Path
    Path absPath = Paths.get("").toAbsolutePath();
    Path directory = absPath.resolve(Paths.get("Database"));
    Path userPath = directory.resolve(Paths.get("user.txt"));

    // Read Data from File
    public List<user> readFile() {
        List<user> users = new ArrayList<>();

        // Read if File Exists
        if (Files.exists(userPath)) {
            try (Scanner userScanner = new Scanner(userPath)) {
                while(userScanner.hasNextLine()) {
                    String[] userDetails = userScanner.nextLine().split(";");
                    users.add(new user(userDetails[0], userDetails[1], userDetails[2], userDetails[3]));
                }
            }
            catch (IOException e) {
                System.out.println("Error Reading File: " + e);
            }
        }

        // Create New Directory & File if File does not Exists
        else {
            try {
                new File(directory.normalize().toString()).mkdirs();
                new File(userPath.normalize().toString()).createNewFile();    
            } catch (IOException e) {
                System.out.println("Error Creating File: " + e);
            }
        }
        return users;
    }

    // Get Specific User Data by ID
    public user getUser(String userID) {
        for (user user : userList) {
            if (user.getID().equals(userID)) {
                return user;
            }
        }
        return null;
    }
}
