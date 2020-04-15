package com.safetynet.alerts.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.safetynet.alerts.DAO.FireStationDAO;
import com.safetynet.alerts.model.FireStation;

@SpringBootTest
@AutoConfigureMockMvc
public class fireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    FireStation fireStationMock;
    
    @MockBean
    FireStationDAO fireStationDAO;
    
    @Test
    public void GiveAllFireStation() throws Exception {
              
        //WHEN //THEN return the station
       
        this.mockMvc.perform(get("/firestation/all"))
                        .andExpect(status().isOk());
  }
}
