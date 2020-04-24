package com.safetynet.alerts.controller;

import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.services.PersonService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;
    @MockBean
    private Person personMock;

    String firstNameConst = "firstnametest";
    String lastNameConst = "lastnametest";
    String addressConst = "addresstest";
    String cityConst = "citytest";
    String zipConst = "ziptest";
    String phoneConst = "phone";
    String emailConst = "emailtest@test.us";

    @BeforeEach
    public void setUpEach() {

        personMock = new Person();
        personMock.setFirstName(firstNameConst);
        personMock.setLastName(lastNameConst);
        personMock.setAddress(addressConst);
        personMock.setCity(cityConst);
        personMock.setZip(zipConst);
        personMock.setPhone(phoneConst);
        personMock.setEmail(emailConst);

    }
    @Test
    public void PersonController_DeletePersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.delete(any(String.class), any(String.class))).thenReturn(true);
        // WHEN
        // THEN
        this.mockMvc
                .perform(delete("/person?firstname=" + firstNameConst + "&lastname=" + lastNameConst)
                        .content(asJsonString(new Person(firstNameConst, lastNameConst, addressConst, cityConst,
                                zipConst, phoneConst, emailConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Deleted person : firstnametest lastnametest"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void PersonController_PutPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.update(any(Person.class))).thenReturn(personMock);
        // WHEN
        // THEN
        this.mockMvc
                .perform(put("/person")
                        .content(asJsonString(new Person(firstNameConst, lastNameConst, addressConst, cityConst,
                                zipConst, phoneConst, emailConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    
    @Test
    public void PersonController_PutNotExistingPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.update(any(Person.class))).thenReturn(null);
        // WHEN
        // THEN
        this.mockMvc
                .perform(put("/person")
                        .content(asJsonString(new Person("firstNameNotExist", lastNameConst, addressConst, cityConst,
                                zipConst, phoneConst, emailConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void PersonController_addPersonTest() throws Exception {

        // GIVEN

        Mockito.when(personService.save(any(Person.class))).thenReturn(personMock);
        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/person")
                        .content(asJsonString(new Person(firstNameConst, lastNameConst, addressConst, cityConst,
                                zipConst, phoneConst, emailConst)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value(firstNameConst))
                .andExpect(jsonPath("$.lastName").value(lastNameConst));

    }

    @Test
    public void PersonController_addPerson_WithWrongEmailTest() throws Exception {

        // GIVEN

        Mockito.when(personService.save(any(Person.class))).thenReturn(personMock);
        // WHEN
        // THEN
        this.mockMvc.perform(post("/person")
                .content(asJsonString(new Person(firstNameConst, lastNameConst, addressConst, cityConst, zipConst,
                        phoneConst, "wrongEmail")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void PersonController_getAnExistingPersonTest() throws Exception {

        // GIVEN
        List<Person> listPersonTest = new ArrayList<>();
        listPersonTest.add(personMock);

        Mockito.when(personService.findPerson(any(String.class), any(String.class))).thenReturn(listPersonTest);
        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/person?firstname=" + firstNameConst + "&lastname=" + lastNameConst)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$..firstName").value(firstNameConst))
                .andExpect(jsonPath("$..lastName").value(lastNameConst));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
