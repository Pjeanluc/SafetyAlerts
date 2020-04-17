package com.safetynet.alerts.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.safetynet.alerts.DAO.FireStationDAO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.services.FireStationService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class fireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FireStation fireStationMock;

    @MockBean
    FireStationService fireStationService;

    @Test
    public void GiveAllFireStationTest() throws Exception {

        // WHEN //THEN return the station

        this.mockMvc.perform(get("/firestation/all")).andExpect(status().isOk());
    }

    @Test
    public void PostFireStationTest() throws Exception {

        // GIVEN
        Mockito.when(fireStationService.save(any(FireStation.class))).thenReturn(new FireStation());

        String payload = "{ \"address\": \"20 rue de Paris\",\"station\": \"3\" }";

        // WHEN //THEN return the station added
        this.mockMvc.perform(post("/firestation").content(payload).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void PutFireStationTestWitheFireStationExisting() throws Exception {

        // GIVEN
        fireStationMock = new FireStation();
        fireStationMock.setAddress("20 rue de Paris");
        fireStationMock.setStation("3");

        Mockito.when(fireStationService.update(any(FireStation.class))).thenReturn(fireStationMock);

        String payload = "{\"address\":\"20 rue de Paris\",\"station\":\"3\"}";

        // WHEN //THEN return the station added
        this.mockMvc
                .perform(put("/firestation").content(payload).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("3"))
                .andExpect(jsonPath("$.address").value("20 rue de Paris"));
    }
    @Test
    public void PutFireStationTestWitheFireStationDotNotExist() throws Exception {

        // GIVEN
        Mockito.when(fireStationService.update(any(FireStation.class))).thenReturn(null);

        String payload = "{ \"address\": \"20 rue de Paris\",\"station\": \"3\" }";

        // WHEN //THEN return the station added
        this.mockMvc
                .perform(put("/firestation").content(payload).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
