package com.example.taskapp.model;

public class Task {
    private Long id;
    private String description;
    private boolean done;

    public Task(Long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + description;
    }
}