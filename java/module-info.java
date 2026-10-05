module com.example.taskapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.taskapp to javafx.fxml;
    opens com.example.taskapp.controller to javafx.fxml;
    opens com.example.taskapp.statistics to javafx.fxml;

    exports com.example.taskapp;
}