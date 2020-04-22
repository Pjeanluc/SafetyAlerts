package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class AlertControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void ChildAlertTest() throws Exception {
        // GIVEN

        // WHEN //THEN
        this.mockMvc.perform(get("/childAlert?address=\"20 rue de Paris\"").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    void PhoneAlertTest() throws Exception {
        // GIVEN

        // WHEN //THEN
        this.mockMvc.perform(get("/phoneAlert?firestation=\"1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}