package com.safetynet.alerts.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.services.FireStationService;
import static com.safetynet.alerts.controller.PersonControllerTest.asJsonString;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class FireStationControllerTest {
   
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;
    
    @MockBean
    private FireStation fireStationMock;

    @Test
    public void FirestationController_addANewFirestationTest() throws Exception {

        //GIVEN : 
        fireStationMock = new FireStation(); 
        fireStationMock.setStation("stationtest");
        fireStationMock.setAddress("addresstest");
        Mockito.when(fireStationService.save(any(FireStation.class))).thenReturn(fireStationMock);

        //WHEN //THEN return the station added
         mockMvc.perform(post("/firestation")
                .content(asJsonString(new FireStation("stationtest","addresstest")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))                
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.station").value("stationtest"))
                .andExpect(jsonPath("$.address").value("addresstest"));

    }
    
    @Test
    public void FirestationController_updateFirestationTest() throws Exception {

        //GIVEN : 
        fireStationMock = new FireStation(); 
        fireStationMock.setStation("stationtestmodified");
        fireStationMock.setAddress("addresstestmodified");
        Mockito.when(fireStationService.update(any(FireStation.class))).thenReturn(fireStationMock);

        //WHEN //THEN return the station added
         mockMvc.perform(put("/firestation")
                .content(asJsonString(new FireStation("stationtest","addresstest")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("stationtestmodified"))
                .andExpect(jsonPath("$.address").value("addresstestmodified"));

    }
    
    @Test
    public void FirestationController_removeFirestationTest() throws Exception {

        //GIVEN : 
        fireStationMock = new FireStation(); 
        fireStationMock.setStation("stationtest");
        fireStationMock.setAddress("addresstest");
        List<FireStation> listFireStation = new ArrayList<>();
        listFireStation.add(fireStationMock);
        Mockito.when(fireStationService.delete(any(FireStation.class))).thenReturn(listFireStation);

        //WHEN //THEN return the station added
         mockMvc.perform(delete("/firestation")
                .content(asJsonString(new FireStation("stationtest","addresstest")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..station").value("stationtest"));

    }
    
    @Test
    public void FirestationController_getCoverageFirestationTest() throws Exception {

        //GIVEN : 
        FireStationCoverage fireStationCoverageMock = new FireStationCoverage();
        fireStationCoverageMock.setAdultCount(1);
        fireStationCoverageMock.setChildCount(2);
        Mockito.when(fireStationService.fireStationPersonsCovered(any(String.class))).thenReturn(fireStationCoverageMock);

        //WHEN //THEN return the station added
         mockMvc.perform(get("/firestation?stationNumber=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))                
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..adultCount").value(1))
                .andExpect(jsonPath("$..childCount").value(2));

    }

}
