package com.example.demo.controller;

public class TaskCreator {
    private String task;
    private Long deadline;

    public TaskCreator(String task, Long deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }
}
