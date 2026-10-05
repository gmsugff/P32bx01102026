package com.example.taskapp.service;

import com.example.taskapp.model.Task;
import com.example.taskapp.util.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public TaskService() {
        initDatabase();
    }

    private void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (id SERIAL PRIMARY KEY, description TEXT NOT NULL, done BOOLEAN DEFAULT FALSE)";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks ORDER BY id";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tasks.add(new Task(rs.getLong("id"), rs.getString("description"), rs.getBoolean("done")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void addTask(String description) {
        String sql = "INSERT INTO tasks (description, done) VALUES (?, false)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void toggleDone(Long taskId) {
        String sql = "UPDATE tasks SET done = NOT done WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, taskId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}