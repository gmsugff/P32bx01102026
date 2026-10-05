package com.example.taskapp.controller;

import com.example.taskapp.MainApp;
import com.example.taskapp.model.Task;
import com.example.taskapp.service.TaskService;
import com.example.taskapp.statistics.StatisticsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainController {

    @FXML private TextField taskInput;
    @FXML private ListView<Task> taskListView;

    private TaskService taskService;
    private StatisticsController statisticsController;

    @FXML
    public void initialize() {
        taskService = new TaskService();
        refreshList();
    }

    @FXML
    public void handleAddTask() {
        String text = taskInput.getText();
        if (text != null && !text.trim().isEmpty()) {
            taskService.addTask(text.trim());
            taskInput.clear();
            refreshList();
        }
    }

    @FXML
    public void handleToggleDone() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskService.toggleDone(selectedTask.getId());
            refreshList();
        }
    }

    @FXML
    public void openStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com.example.taskapp/statistics/statistics.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), 300, 200));
            stage.setTitle("Статистика");

            statisticsController = loader.getController();
            updateStatisticsWindow();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshList() {
        List<Task> tasks = taskService.getAllTasks();
        ObservableList<Task> observableTasks = FXCollections.observableArrayList(tasks);
        taskListView.setItems(observableTasks);
        updateStatisticsWindow();
    }

    private void updateStatisticsWindow() {
        if (statisticsController != null) {
            statisticsController.updateStatistics(taskService.getAllTasks());
        }
    }
}