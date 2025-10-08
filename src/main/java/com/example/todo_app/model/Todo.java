package com.example.todo_app.model;

import java.time.LocalDate;

public class Todo {
    private long id;
    private String title;
    private String assign;
    private String ownerUsername;
    private String description;
    private LocalDate targetDate;
    private boolean status;

    protected Todo(){};

    public Todo(long id, String title, String assign, String ownerUsername,
                String description, LocalDate targetDate,
                boolean status) {
        this.id = id;
        this.title = title;
        this.assign = assign;
        this.ownerUsername = ownerUsername;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;

    }

    public Todo(String title, String assign, String ownerUsername,
                String description, LocalDate targetDate,
                boolean status) {
        this.title = title;
        this.assign = assign;
        this.ownerUsername = ownerUsername;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;

    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssign() {
        return assign;
    }

    public void setUsername(String username) {
        this.assign = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
