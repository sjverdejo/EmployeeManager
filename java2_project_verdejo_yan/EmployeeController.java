package java2_project_verdejo_yan;

import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;

// @author Samuel Verdejo
// @author Jiaxin Yan
// date: April 3, 2023

//Controller class: to update view using the model, displays employees, adds new employees, and deletes employees
public class EmployeeController implements Initializable {
    //ArrayList for employees
    List<Employee> employees = new ArrayList<>();

    //ListView for employees
    @FXML
    private ListView<Employee> employeeList;

    //All the buttons
    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;

    //Textfields
    @FXML
    private TextField employeeId;

    @FXML
    private TextField employeeName;

    //ComboBox
    @FXML
    private ComboBox<String> employeeJob;

    //CheckBox
    @FXML
    private CheckBox employeeFullTime;

    //Genders toggle group
    ToggleGroup genders = new ToggleGroup();

    //toggles
    @FXML
    private RadioButton employeeMale;

    @FXML
    private RadioButton employeeFemale;

    @FXML
    private RadioButton employeeOther;

    //Labels for both Employee Count and Status
    @FXML
    private Label employeesCount;

    @FXML
    private Label statusLabel;

    //Add employee button action that validates input and if valid, adds employee to ArrayList and
    //updates ListView
    @FXML
    void addEmployee(ActionEvent event) {
        //Check if TextFields are empty, ComboBox not selected, or no RadioButtons selected
        if (employeeId.getText().isEmpty() || employeeName.getText().isEmpty() ||
                employeeJob.getSelectionModel().getSelectedItem() == null || genders.getSelectedToggle() == null) {
            statusLabel.setText("Status: Invalid - All fields must be completed."); //update status
            return; //return if not valid
        }

        int id = 0; //employee ID integer

        //try catch block to parseInt from TextField, if unsuccessful, return and update status
        try {
            id = Integer.parseInt(employeeId.getText());
        } catch (NumberFormatException ex) {
            statusLabel.setText("Status: Invalid - ID must only contain numbers.");
            return;
        }

        //Check if id is not negative
        if (id < 0) {
            //if negative, update status and return
            statusLabel.setText("Status: Invalid - ID must be positive number.");
            return;
        }

        //Check if id already exists in employee ArrayList
        for (Employee e: employees) {
            if (id == e.getId()) {
                //if found, update status and return
                statusLabel.setText("Status: Invalid - ID " + id + " already exists.");
                return;
            }
        }

        //set fulltime to CheckBox value
        boolean fulltime = employeeFullTime.isSelected();

        //set name to TextField value
        String name = employeeName.getText();

        //set job to selected ComboBox value
        String job = employeeJob.getSelectionModel().getSelectedItem();

        //set gender to selected RadioButton value
        String gender = "";

        if (employeeMale.isSelected()) {
            gender = "Male";
        } else if (employeeFemale.isSelected()) {
            gender = "Female";
        } else if (employeeOther.isSelected()) {
            gender = "Other";
        } else {
            //if no radio button selected, update status and return
            statusLabel.setText("Status: Invalid - Must select a gender.");
            return;
        }

        //add new employee to ArrayList of employees
        employees.add(new Employee(id, name, job, fulltime, gender));

        //display the updated list
        displayEmployees(employeeList, employees);

        //update status
        statusLabel.setText("Status: Valid - Employee " + id + " added successfully.");
    }

    //Delete employee button action that finds and removes the selected ListView item from the employee
    //ArrayList and updates the ListView as well
    @FXML
    void deleteEmployee(ActionEvent event) {
        Employee selected = employeeList.getSelectionModel().getSelectedItem(); //declare variable for selected employee
        employees.remove(selected); //delete selected employee
        displayEmployees(employeeList, employees); //display updated employees
        statusLabel.setText("Status: Successfully deleted Employee " + selected.getId() + "."); //update status
    }

    //Save employees button to save current list of employees to the CSV file
    @FXML
    void saveToEmployees(ActionEvent event) {
        FileHelper.saveToCSV(employees); //call FileHelper static method to save employee ArrayList to CSV file
        statusLabel.setText("Status: Employees saved to file."); //update status
    }

    //DisplayEmployees method to add ArrayList of employees to ListView
    public void displayEmployees(ListView<Employee> employeesView, List<Employee> employees) {
        employeesView.getItems().clear(); //clear current employee ListView
        employeesView.getItems().addAll(employees); //add current ArrayList of employees
        employeesView.refresh(); //refresh ListView
        employeesCount.setText(Integer.toString(employees.size())); //Set employee count to updated count
        statusLabel.setText("Status:"); //Reset status
    }

    //Clear button to clear all inputs
    @FXML
    void clearInput(ActionEvent event) {
        statusLabel.setText("Status:"); //reset status
        employeeId.clear(); //clear TextField for ID
        employeeName.clear(); //clear TextField for name
        employeeJob.getSelectionModel().clearSelection(); //clear selected job
        genders.selectToggle(null); //clear selected toggle for gender
        employeeFullTime.setSelected(false); //clear employee fulltime Checkbox
    }

    //Initialize app on startup
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileHelper.loadFromCSV(employees); //FileHelper static method to load into employees ArrayList from CSV file
        displayEmployees(employeeList, employees); //display the updated employees

        //Job combo box initialization
        List<String> jobs = new ArrayList<>();
        Collections.addAll(jobs, "Director", "Manager", "Developer", "Tester", "Salesman");
        employeeJob.getItems().addAll(jobs);

        //Gender radio button initialization
        genders.getToggles().addAll(employeeMale, employeeFemale, employeeOther);
    }
}

