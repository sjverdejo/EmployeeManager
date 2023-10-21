package java2_project_verdejo_yan;

import java.util.*;
import java.io.*;

// @author Samuel Verdejo
// @author Jiaxin Yan
// date: April 3, 2023

//This class is a helper class for the main application with two methods to help load from and save to a CSV file
public class FileHelper {
    //The main CSV file application will use
    final static String filename = "Employees.csv";

    //Static method to load employees from a CSV file into a given ArrayList
    public static void loadFromCSV(List<Employee> employees) {
        //create new instance of file
        File file = new File(filename);

        //if file for some reason does not exist, create new file
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Creating new file failed.");
        }

        //Create scanner instance to read from file
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNext()) {
                //store scanner input into array
                String line = reader.nextLine();
                String lines[] = line.split(",");

                //create Employee instance and add to ArrayList
                employees.add(new Employee(Integer.parseInt(lines[0]), lines[1], lines[2], Boolean.parseBoolean(lines[3]), lines[4]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file.");
        }
    }


    //Static method to save an ArrayList of employees to the CSV file
    public static void saveToCSV(List<Employee> employees) {
        //Create new file instance
        File file = new File(filename);

        //create file if does not exist already in try catch block
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.println("Creating new file failed");
        }

        //Create new PrintWriter instance in try-catch with resource block
        try (PrintWriter writer = new PrintWriter(file)) {
            //loop through ArrayList of employees and write to file CSV format
            for (Employee e: employees) {
                writer.println(e.getId() + "," + e.getName() + "," + e.getJob() + "," + e.isFulltime() + "," + e.getGender());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file.");
        }
    }
}
