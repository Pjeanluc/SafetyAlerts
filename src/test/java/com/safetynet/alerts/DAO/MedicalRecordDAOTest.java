package com.safetynet.alerts.DAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.safetynet.alerts.model.MedicalRecord;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class MedicalRecordDAOTest {

    @Autowired
    MedicalRecordsDAO medicalRecordDAO;

    @Test
    public void getMedicalExistingRecordTest() throws ParseException {

        // GIVEN
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse("01/01/2001");
        List<String> medications = new ArrayList<String>();
        medications.add("med1");
        List<String> allergies = new ArrayList<String>();
        allergies.add("allergi1");

        // WHEN
        MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecords("Firstnametest1","Lastnametest1");

        // THEN
        assertThat(medicalRecord.getBirthdate()).isCloseTo(birthDay, within(3600000,ChronoUnit.MILLIS).getValue());
        assertThat(medicalRecord.getMedications()).isEqualTo(medications);
        assertThat(medicalRecord.getAllergies()).isEqualTo(allergies);
    }
    
    @Test
    public void getMedicalNotExistingRecordTest() throws ParseException {

        // GIVEN       
        // WHEN
        // THEN
        assertThat(medicalRecordDAO.getMedicalRecords("FirstnametestNotExist","LastnametestNotExist")).isNull();
        
    }
    
    @Test
    public void saveOneMedicalRecordTest() throws ParseException {

        // GIVEN        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse("01/01/1990");
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");

        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest3", "Lastnametest3", birthDay, medications,
                allergies);

        // WHEN
        MedicalRecord medicalRecordAddedd = medicalRecordDAO.addMedicalRecords(medicalRecordTest);

        // THEN
        assertThat(medicalRecordAddedd.getFirstName()).isEqualTo(medicalRecordTest.getFirstName());
        assertThat(medicalRecordAddedd.getLastName()).isEqualTo(medicalRecordTest.getLastName());
        assertThat(medicalRecordAddedd.getBirthdate()).isEqualTo(medicalRecordTest.getBirthdate());
        assertThat(medicalRecordAddedd.getMedications()).isEqualTo(medicalRecordTest.getMedications());
        assertThat(medicalRecordAddedd.getAllergies()).isEqualTo(medicalRecordTest.getAllergies());

    }

    @Test
    public void updateOneExistingMedicationRecordTest() throws ParseException {

        // GIVEN
        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");

        MedicalRecord medicalRecordTest = new MedicalRecord("Firstnametest1", "Lastnametest1", birthDay, medications,
                allergies);
        // WHEN
        MedicalRecord medicalRecordUpdated = medicalRecordDAO.updateMedicalRecords(medicalRecordTest);

        // THEN
        assertThat(medicalRecordUpdated.getFirstName()).isEqualTo(medicalRecordTest.getFirstName());
        assertThat(medicalRecordUpdated.getLastName()).isEqualTo(medicalRecordTest.getLastName());
        assertThat(medicalRecordUpdated.getBirthdate()).isEqualTo(medicalRecordTest.getBirthdate());
        assertThat(medicalRecordUpdated.getMedications()).isEqualTo(medicalRecordTest.getMedications());
        assertThat(medicalRecordUpdated.getAllergies()).isEqualTo(medicalRecordTest.getAllergies());

    }

    @Test
    public void updateNotExistingMedicationRecordTest() throws ParseException {

        // GIVEN
        String stringDate = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");

        MedicalRecord medicalRecordTest = new MedicalRecord("FirstnametestNotExist", "LastnametestNotExist", birthDay,
                medications, allergies);
        // WHEN
        // THEN
        assertThat(medicalRecordDAO.updateMedicalRecords(medicalRecordTest)).isNull();
    }

    @Test
    public void deleteOneExistingMedicalRecordTest() {

        // GIVEN
        // WHEN
        // THEN
        assertThat(medicalRecordDAO.deleteMedicalRecords("firstnamedeletetest", "lastnamedeletetest")).isTrue();

    }

    @Test
    public void deleteNotExistingMedicalRecordTest() {

        // GIVEN
        // WHEN
        // THEN
        assertThat(medicalRecordDAO.deleteMedicalRecords("firstnamenotexist", "lastnamenotexist")).isFalse();

    }

}
