package com.example.demo.controller;

import com.example.demo.service.TaskService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@CrossOrigin
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public JSONArray getTasks(){
        return taskService.getTasks();
    }


    @GetMapping("/{id}")
    public TaskEntity getTask(@PathVariable("id") String id){
        return taskService.getTask(id);
    }

    @PostMapping("/")
    public TaskEntity addTask(@RequestBody TaskCreator taskCreator){
        return taskService.createTask(taskCreator);
    }

    @DeleteMapping("/{id}")
    public String removeTask(@PathVariable("id") UUID id){
        return taskService.removeTaskJSON(id);
    }
}
