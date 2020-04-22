package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
class FireControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getFireListPersonWithExistingAddressTest() throws Exception {
        // GIVEN
        // WHEN //THEN
        String body ="[{\"firstName\":\"Firstnametest1\",\"lastName\":\"Lastnametest1\",\"phone\":\"phonetest1\",\"age\":19,\"medications\":[\"med1\"],\"allergies\":[\"allergi1\"],\"station\":\"1\"},{\"firstName\":\"Firstnametest11\",\"lastName\":\"Lastnametest1\",\"phone\":\"phonetest1\",\"age\":0,\"medications\":null,\"allergies\":null,\"station\":\"1\"}]";
        this.mockMvc.perform(get("/fire?address=adresstest1").accept(MediaType.APPLICATION_JSON))
        .andDo(print())
                .andExpect(content().string(body)).andExpect(status().isOk());
    }

    @Test
    void getFireListPersonWithNotExistingAddressTest() throws Exception {
        // GIVEN
        // WHEN //THEN
        this.mockMvc.perform(get("/fire?address=adresstestnotexist").accept(MediaType.APPLICATION_JSON))
        .andDo(print())
                .andExpect(content().string("[]")).andExpect(status().isOk());
    }

}
