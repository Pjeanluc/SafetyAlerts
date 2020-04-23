package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.services.FireStationService;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class ComplementControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    FireStationService fireStationService;

    @Test
    public void getCoverageFireStation() throws Exception {

        // GIVEN

        // WHEN //THEN
        String body = "{\"fireStationPersons\":[{\"fistName\":\"Firstnametest1\",\"address\":\"adresstest1\","
                + "\"phone\":\"phonetest1\",\"lasName\":\"Lastnametest1\"},{\"fistName\":\"Firstnametest11\","
                + "\"address\":\"adresstest1\",\"phone\":\"phonetest1\",\"lasName\":\"Lastnametest1\"}],"
                + "\"adultCount\":1,\"childCount\":1}";
        this.mockMvc.perform(get("/firestation?stationNumber=1").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(content().json(body));

    }

}
