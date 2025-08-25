package main.java.domain;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class addNewUser {
    Path absPath = Paths.get("").toAbsolutePath();
    Path directory = absPath.resolve(Paths.get("Database"));
    Path userPath = directory.resolve(Paths.get("user.txt"));
    
    // Write User List to File
    public void WriteFile(List<user> userList) {

        // Write if File Exists
        if (Files.exists(userPath)) {
            try (FileWriter userWriter = new FileWriter(userPath.normalize().toString())) {
                for (user user : userList) {
                    userWriter.write(user.toString());
                }
            }
            catch (IOException e) {
                System.out.println("Error Writing to File: " + e);
            }
        }

        // Create New Directory & File if File does not Exists
        else {
            try {
                new File(directory.normalize().toString()).mkdirs();
                new File(userPath.normalize().toString()).createNewFile();
                
            } 
            catch (IOException e) {
                System.out.println("Error Creating File: " + e);
            }
        }
    }



}
