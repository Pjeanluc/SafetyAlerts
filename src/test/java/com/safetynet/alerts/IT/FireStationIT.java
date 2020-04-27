package com.safetynet.alerts.IT;

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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.services.FireStationService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class FireStationIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FireStation fireStationMock;

    @MockBean
    FireStationService fireStationService;

    @Test
    public void PostFireStationTest() throws Exception {

        // GIVEN
        Mockito.when(fireStationService.save(any(FireStation.class)))
                .thenReturn(new FireStation("20 rue de Paris", "3"));

        String payload = "{ \"address\": \"20 rue de Paris\",\"station\": \"3\" }";

        // WHEN //THEN
        this.mockMvc.perform(post("/firestation").content(payload).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void PutFireStationWitheFireStationExistingTest() throws Exception {

        // GIVEN
        fireStationMock = new FireStation("20 rue de Paris", "3");

        Mockito.when(fireStationService.update(any(FireStation.class))).thenReturn(fireStationMock);

        String payload = "{\"address\":\"20 rue de Paris\",\"station\":\"3\"}";

        // WHEN //THEN
        this.mockMvc
                .perform(put("/firestation").content(payload).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.station").value("3"))
                .andExpect(jsonPath("$.address").value("20 rue de Paris"));
    }

    @Test
    public void PutFireStationWitheFireStationDotNotExistTest() throws Exception {

        // GIVEN
        Mockito.when(fireStationService.update(any(FireStation.class))).thenReturn(null);

        String payload = "{ \"address\": \"20 rue de Paris\",\"station\": \"3\" }";

        // WHEN //THEN
        this.mockMvc.perform(put("/firestation").content(payload).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void deleteFireStationWitheFireStationExistTest() throws Exception {

        // GIVEN
        List<FireStation> listFireStationMock = new ArrayList<>();
        fireStationMock = new FireStation("20 rue de Paris", "3");
        listFireStationMock.add(fireStationMock);
        Mockito.when(fireStationService.delete(any(FireStation.class))).thenReturn(listFireStationMock);

        String payload = "{ \"address\": \"20 rue de Paris\",\"station\": \"3\" }";

        // WHEN //THEN
        this.mockMvc
                .perform(delete("/firestation").content(payload).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$..station").value("3"))
                .andExpect(jsonPath("$..address").value("20 rue de Paris"));
    }

    @Test
    public void deleteFireStationWitheFireStationDotNotExistTest() throws Exception {

        // GIVEN
        List<FireStation> listFireStationMock = new ArrayList<>();
        Mockito.when(fireStationService.delete(any(FireStation.class))).thenReturn(listFireStationMock);

        String payload = "{ \"address\": \"20 rue de Paris\",\"station\": \"3\" }";

        // WHEN //THEN
        this.mockMvc.perform(delete("/firestation").content(payload).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void getCoverageFireStationTest() throws Exception {

        // GIVEN

        Mockito.when(fireStationService.fireStationPersonsCovered(any(String.class)))
                .thenReturn(new FireStationCoverage());

        // WHEN //THEN
        this.mockMvc.perform(get("/firestation?stationNumber=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
