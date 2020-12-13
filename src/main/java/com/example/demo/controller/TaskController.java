package com.example.demo.controller;

import com.example.demo.service.TaskService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/** Class containing all the server request endpoints
 *
 * getTasks() - returns all tasks from tasks.json
 * getTask(String id) - returns a task by a provided id
 * addTask(TaskCreator taskCreator) creates a new task
 * removeTask(UUID id) - removes a task by a provided id
 */

@CrossOrigin
@RestController
@RequestMapping("task")
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
