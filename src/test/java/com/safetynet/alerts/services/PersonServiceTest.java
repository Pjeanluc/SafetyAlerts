package com.safetynet.alerts.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.safetynet.alerts.DAO.PersonDAO;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.ChildInfo;
import com.safetynet.alerts.model.url.CommunityEmail;
import com.safetynet.alerts.model.url.InfoPerson;
import com.safetynet.alerts.model.url.PhoneInfo;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class PersonServiceTest {

    @Autowired
    PersonService personServiceTest;

    @MockBean
    PersonDAO personDAOMock;

    @MockBean
    MedicalRecordService medicalRecordServiceMock;

    @MockBean
    FireStationService fireStationServiceMock;

    @MockBean
    ServiceUtil serviceUtilMock;

    @Test
    void getListChildTest() throws Exception {
        // GIVEN
        List<Person> listPersonsTest = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        listPersonsTest.add(person1);
        listPersonsTest.add(person2);
        Mockito.when(personDAOMock.findPersonByAdress(any(String.class))).thenReturn(listPersonsTest);

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");
        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest3", "Lastnametest3", birthDay, medications,
                allergies);
        Mockito.when(medicalRecordServiceMock.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecordTest);

        Mockito.when(serviceUtilMock.calculateAge(any(Date.class))).thenReturn(10);
        Mockito.when(serviceUtilMock.isChild(any(int.class))).thenReturn(true);

        // WHEN
        List<ChildInfo> listChildInfoTest = personServiceTest.getListChild("addresstest");

        // THEN
        assertThat(listChildInfoTest.size()).isEqualTo(2);

    }

    @Test
    void getListPhoneInfoTest() throws ParseException {
        // GIVEN
        List<String> listAdressTest = new ArrayList<>();
        listAdressTest.add("adressetest");
        Mockito.when(fireStationServiceMock.addressCoveredByStation(any(String.class))).thenReturn(listAdressTest);

        List<Person> listPersonsTest = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        listPersonsTest.add(person1);
        listPersonsTest.add(person2);
        Mockito.when(personDAOMock.findPersonByAdress(any(String.class))).thenReturn(listPersonsTest);

        // WHEN
        List<PhoneInfo> listPhoneInfoTest = personServiceTest.getListPhoneInfo("1");

        // THEN
        assertThat(listPhoneInfoTest.size()).isEqualTo(2);
    }

    @Test
    void getPersonInfoTest() throws ParseException {
        // GIVEN
        List<Person> listPersonsTest = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        listPersonsTest.add(person1);
        listPersonsTest.add(person2);
        Mockito.when(personDAOMock.findPersonByFirstNameAndLastName(any(String.class), any(String.class)))
                .thenReturn(listPersonsTest);

        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");
        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest3", "Lastnametest3", birthDay, medications,
                allergies);
        Mockito.when(medicalRecordServiceMock.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecordTest);

        // WHEN
        List<InfoPerson> listInfoPerson = personServiceTest.getPersonInfo("firstnametest", "lastnameTest");

        // THEN
        assertThat(listInfoPerson.size()).isEqualTo(2);
    }

    @Test
    void getCommunityEmailWithExistingCityTest() {
        // GIVEN
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person("firstname", "lastname", "", "City", "", "", "email1");
        Person person2 = new Person("firstname", "lastname", "", "City", "", "", "email2");
        persons.add(person1);
        persons.add(person2);
        Mockito.when(personDAOMock.getAllPersons()).thenReturn(persons);
        // WHEN
        List<CommunityEmail> communityEmailTest = personServiceTest.getCommunityEmail("City");
        // THEN
        assertThat(communityEmailTest.size()).isEqualTo(2);
    }

    @Test
    void getCommunityEmailWithCityNotExistTest() {
        // GIVEN
        Mockito.when(personDAOMock.getAllPersons()).thenReturn(new ArrayList<>());
        // WHEN
        // THEN
        assertThat(personServiceTest.getCommunityEmail("Citynotexist").isEmpty());
    }

}
