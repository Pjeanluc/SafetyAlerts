package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class CommunityEmailControllerTestIT {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void CommunityEmaiforAnExistingCityTest() throws Exception {
        // GIVEN

        // WHEN //THEN
        String body = "[{\"email\":\"emailtest1@email.com\"},{\"email\":\"emailtest1@email.com\"},"
        +"{\"email\":\"emailtest1@email.com\"}]";
        this.mockMvc.perform(get("/communityEmail?city=Citytest1").accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(content().json(body))
        .andExpect(status().isOk());
    }

}
