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

import com.safetynet.alerts.model.url.CommunityEmail;
import com.safetynet.alerts.model.url.FloodHome;
import com.safetynet.alerts.model.url.InfoPersonFull;
import com.safetynet.alerts.services.FireStationService;
import com.safetynet.alerts.services.MedicalRecordService;
import com.safetynet.alerts.services.PersonService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class ComplementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordServiceMock;

    @MockBean
    private PersonService personServiceMock;

    @MockBean
    private FireStationService fireStationServiceMock;

    @Test
    public void CommunityEmailController_Test() throws Exception {

        // GIVEN :
        CommunityEmail communityEmailMock = new CommunityEmail();
        communityEmailMock.setEmail("emailtest");

        List<CommunityEmail> listCommunityEmail = new ArrayList<>();
        listCommunityEmail.add(communityEmailMock);
        Mockito.when(personServiceMock.getCommunityEmail(any(String.class))).thenReturn(listCommunityEmail);

        // WHEN
        // THEN
        mockMvc.perform(get("/communityEmail?city=citytest")

                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..email").value("emailtest"));

    }

    @Test
    public void FireController_Test() throws Exception {

        // GIVEN :
        InfoPersonFull fireListPersonMock = new InfoPersonFull();
        fireListPersonMock.setAge(10);
        fireListPersonMock.setFirstName("firstnametest");
        fireListPersonMock.setLastName("lastnametest");
        fireListPersonMock.setPhone("phonetest");
        fireListPersonMock.setStation("stationtest");

        List<InfoPersonFull> listFireListPerson = new ArrayList<>();
        listFireListPerson.add(fireListPersonMock);
        Mockito.when(fireStationServiceMock.getFireListPerson(any(String.class))).thenReturn(listFireListPerson);

        // WHEN
        // THEN
        mockMvc.perform(get("/fire?address=addresstest").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstnametest"))
                .andExpect(jsonPath("$..lastName").value("lastnametest")).andExpect(jsonPath("$..age").value(10))
                .andExpect(jsonPath("$..phone").value("phonetest"));

    }

    @Test
    public void FloodController_Test() throws Exception {

        // GIVEN :
        FloodHome floodHomeMock = new FloodHome();
        floodHomeMock.setAddress("addresstest");

        List<FloodHome> listFloodHomeMock = new ArrayList<>();
        listFloodHomeMock.add(floodHomeMock);
        List<String> listStation = new ArrayList<>();
        listStation.add("1");
        Mockito.when(fireStationServiceMock.getFloodListHome(listStation)).thenReturn(listFloodHomeMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(
                get("/flood?stations=1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$..address").value("addresstest"));

    }

    @Test
    public void PersonInfoController_Test() throws Exception {

        // GIVEN :
        InfoPersonFull infoPersonMock = new InfoPersonFull();
        infoPersonMock.setFirstName("firstnametest");
        infoPersonMock.setLastName("lastnametest");
        infoPersonMock.setEmail("emailtest@email.us");
        infoPersonMock.setAge(10);
        infoPersonMock.setAddress("addresstest");

        List<InfoPersonFull> listInfoPersonMock = new ArrayList<>();
        listInfoPersonMock.add(infoPersonMock);

        Mockito.when(personServiceMock.getPersonInfo(any(String.class), any(String.class)))
                .thenReturn(listInfoPersonMock);

        // WHEN //THEN return the medical record
        mockMvc.perform(get("/personInfo?firstName=firstnametest&lastName=lastnametest")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstnametest"))
                .andExpect(jsonPath("$..lastName").value("lastnametest"))
                .andExpect(jsonPath("$..email").value("emailtest@email.us")).andExpect(jsonPath("$..age").value(10))
                .andExpect(jsonPath("$..address").value("addresstest"));

    }

}
