package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        mockMvc.perform(get("/").contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void registrationWorksThroughAllLayers2() throws Exception {
        mockMvc.perform(get("/").contentType("application/json")
                .param("id", "cef0e5e7-2c34-4252-9586-83a0ae12b63b"))
                .andExpect(status().isOk());
    }

    @Test
    void registrationWorksThroughAllLayer3() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TaskCreator taskCreator = new TaskCreator("Task test", 1640383200000L);
        mockMvc.perform(post("/").contentType("application/json")
                .content(mapper.writeValueAsString(taskCreator))).andExpect(status().isOk());
    }
    @Test
    void registrationWorksThroughAllLayer4() throws Exception {
        String id = "bc8e21a3-673d-4d29-a617-2df7947be306";
        mockMvc.perform(delete("/{id}", id).contentType("application/json"))
                .andExpect(status().isOk());
    }
}
