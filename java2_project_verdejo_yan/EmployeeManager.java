package java2_project_verdejo_yan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//main class
// @author Samuel Verdejo
// @author Jiaxin Yan
// date: April 3, 2023

public class EmployeeManager extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("EmployeeView.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Employee Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
