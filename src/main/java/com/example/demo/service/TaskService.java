package com.example.demo.service;

import com.example.demo.controller.TaskCreator;
import com.example.demo.controller.TaskEntity;
import com.example.demo.exceptions.InternalServerErrorException;
import com.example.demo.exceptions.NotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/** Service class that handles all the data manipulation
 *
 * getTasks() - returns all tasks from tasks.json
 * getTask(String id) - returns a task by a provided id from tasks.json
 * createTask(TaskCreator taskCreator) creates a new task
 * removeTaskJSON(UUID id) - reads tasks.json and removes a task by a provided id
 * readTasksJSON() - reads tasks.json and returns all tasks as a List
 * readTaskByIdJSON() - reads tasks.json and returns a task by id
 * addTaskJSON() - reads tasks.json and writes a new task to the file
 */

@Service
public class TaskService {

    public JSONArray getTasks() {
        return readTasksJSON();
    }

    public TaskEntity getTask(String id) {
        return readTaskByIdJSON(id);
    }

    public TaskEntity createTask(TaskCreator taskCreator) {
        String task = taskCreator.getTask();
        Long deadline = taskCreator.getDeadline();
        TaskEntity taskEntity = new TaskEntity(task, deadline);

        return addTaskJSON(taskEntity);
    }

    public String removeTaskJSON(UUID uuid){
        String id = uuid.toString();
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/tasks.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray taskArray = (JSONArray) obj;
            for(int i = taskArray.size()-1; i >= 0; i--){
                JSONObject task = (JSONObject) taskArray.get(i);
                String taskId = (String) task.get("id");
                if(taskId.equals(id)){
                    taskArray.remove(i);
                    break;
                }
            }

            //Write JSON file
            FileWriter file = new FileWriter("src/main/resources/tasks.json");

            file.write(taskArray.toJSONString());
            file.flush();
            return id;



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        throw new InternalServerErrorException("Internal server error");
    }

    public JSONArray readTasksJSON(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/tasks.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray tasksArray = (JSONArray) obj;

            //for(int i = 0; i < tasksArray.size(); i++){

                //JSONObject task = (JSONObject) tasksArray.get(i);
                //String taskId = (String) task.get("id");
                //UUID taskUUID = UUID.fromString(taskId);
                //String taskBody = (String) task.get("task");
                //String taskCreatedAt = (String) task.get("createdAt");
                //String taskDeadline = (String) task.get("deadline");
            //}

            return tasksArray;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TaskEntity readTaskByIdJSON(String id){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/tasks.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray taskArray = (JSONArray) obj;

            for(int i = 0; i < taskArray.size(); i++){
                JSONObject task = (JSONObject) taskArray.get(i);
                String taskId = (String) task.get("id");
                if(taskId.equals(id)){
                    TaskEntity taskEntity = new TaskEntity();
                    taskEntity.setId(UUID.fromString(id));
                    taskEntity.setTask((String) task.get("task"));
                    taskEntity.setCreatedAt((Long.parseLong((String) task.get("createdAt"))));
                    taskEntity.setDeadline((Long.parseLong((String) task.get("deadline"))));
                    return taskEntity;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        throw new NotFoundException("Task not found");
    }

    public TaskEntity addTaskJSON(TaskEntity taskEntity){
        JSONObject taskDetails = new JSONObject();
        taskDetails.put("id", taskEntity.getId().toString());
        taskDetails.put("task", taskEntity.getTask());
        taskDetails.put("createdAt", taskEntity.getCreatedAt().toString());
        taskDetails.put("deadline", taskEntity.getDeadline().toString());

        JSONArray taskArray = readTasksJSON();
        taskArray.add(taskDetails);

        //Write JSON file
        try (FileWriter file = new FileWriter("src/main/resources/tasks.json")) {

            file.write(taskArray.toJSONString());
            file.flush();
            return taskEntity;

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new InternalServerErrorException("Adding task failed. Internal Server Error");
    }
}
