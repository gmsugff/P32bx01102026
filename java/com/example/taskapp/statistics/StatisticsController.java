package com.example.taskapp.statistics;

import com.example.taskapp.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class StatisticsController {

    @FXML private Label totalTasksLabel;
    @FXML private Label completedTasksLabel;
    @FXML private Label completionPercentLabel;


    public void updateStatistics(List<Task> tasks) {
        int total = tasks.size();
        long completed = tasks.stream().filter(Task::isDone).count();
        double percent = total == 0 ? 0.0 : ((double) completed / total) * 100;

        totalTasksLabel.setText("Общее количество задач: " + total);
        completedTasksLabel.setText("Выполнено: " + completed);
        completionPercentLabel.setText(String.format("Процент завершения: %.1f%%", percent));
    }
}