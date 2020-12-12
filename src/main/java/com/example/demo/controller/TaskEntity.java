package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TaskEntity {
    private UUID id;
    private String task;
    private Long createdAt;
    private Long deadline;

    public TaskEntity(){}

    public TaskEntity(String task, Long deadline) {
        this.id = UUID.randomUUID();
        this.task = task;
        this.deadline = deadline;
        this.createdAt = new Date().getTime();
    }

    public void setId(UUID id) {
        this.id =  id;
    }

    public UUID getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }
}
