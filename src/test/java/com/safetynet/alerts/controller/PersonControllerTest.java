package com.safetynet.alerts.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.services.PersonService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    Person personMock;

    @MockBean
    PersonService personService;

    final String FIRSTNAME = "firstnametest";
    final String LASTNAME = "lastnametest";
    final String PAYLOAD = "{\"firstname\":\"firstnametest\",\"lastname\":\"lastnametest\","
            + "\"address\":\"\",\"city\":\"\",\"zip\":\"\",\"phone\":\"\",\"email\":\"\"}";

    @Test
    public void GiveOneExistingPersonTest() throws Exception {

        // GIVEN

        personMock = new Person();
        personMock.setFirstName(FIRSTNAME);
        personMock.setLastName(LASTNAME);

        Mockito.when(personService.findPerson(any(String.class), any(String.class))).thenReturn(personMock);

        // WHEN //THEN
        this.mockMvc
                .perform(get("/person?firstname=" + FIRSTNAME + "&lastname=" + LASTNAME)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value(FIRSTNAME))
                .andExpect(jsonPath("$.lastName").value(LASTNAME));
    }

    @Test
    public void GiveNotExistingPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.findPerson(any(String.class), any(String.class))).thenReturn(null);

        // WHEN //THEN
        this.mockMvc
                .perform(get("/person?firstname=" + FIRSTNAME + "&lastname=" + LASTNAME)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void PutOneExistingPersonTest() throws Exception {

        // GIVEN

        personMock = new Person();
        personMock.setFirstName(FIRSTNAME);
        personMock.setLastName(LASTNAME);

        Mockito.when(personService.update(any(Person.class))).thenReturn(personMock);

        // WHEN //THEN
        this.mockMvc
                .perform(put("/person").content(PAYLOAD).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value(FIRSTNAME))
                .andExpect(jsonPath("$.lastName").value(LASTNAME));
    }

    @Test
    public void PutNotExistingPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.update(any(Person.class))).thenReturn(null);

        // WHEN //THEN
        this.mockMvc.perform(put("/person").content(PAYLOAD).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void DeleteOneExistingPersonTest() throws Exception {

        // GIVEN
        Mockito.when(personService.delete(any(String.class), any(String.class))).thenReturn(true);

        // WHEN //THEN

        this.mockMvc
                .perform(delete("/person?firstname=" + FIRSTNAME + "&lastname=" + LASTNAME)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void DeleteNotExistingPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.delete(any(String.class), any(String.class))).thenReturn(false);

        // WHEN //THEN
        this.mockMvc
                .perform(delete("/person?firstname=" + FIRSTNAME + "&lastname=" + LASTNAME).content(PAYLOAD)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
