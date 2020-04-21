package com.safetynet.alerts.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.DAO.MedicalRecordsDAO;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

@Repository
public class MedicalRecordServiceImpl implements MedicalRecordService {
    @Autowired
    private MedicalRecordsDAO MedicalRecordsDAO;

    @Autowired
    private PersonService personService;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public List<MedicalRecord> findAll() {
        return MedicalRecordsDAO.getAllMedicalRecords();
    }

    @Override
    public MedicalRecord findMedicalRecord(String firstName, String lastName) {
        return MedicalRecordsDAO.getMedicalRecords(firstName, lastName);
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {

        if (personService.findPerson(medicalRecord.getFirstName(), medicalRecord.getLastName()) != null) {
            return MedicalRecordsDAO.addMedicalRecords(medicalRecord);
        }

        return null;
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord) {

        return MedicalRecordsDAO.updateMedicalRecords(medicalRecord);
    }

    @Override
    public Boolean delete(String firstName, String lastName) {

        return MedicalRecordsDAO.deleteMedicalRecords(firstName, lastName);
    }

    @Override
    public Boolean isChild(Person person) throws ParseException {
        int agePerson = 0;

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord = findMedicalRecord(person.getFirstName(), person.getLastName());

        if (medicalRecord != null) {
            agePerson = serviceUtil.calculateAge(medicalRecord.getBirthdate());
        }

        if (agePerson <= 18) {
            return true;
        } else {
            return false;
        }
    }
}
