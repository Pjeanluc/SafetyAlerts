package com.safetynet.alerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.DAO.MedicalRecordsDAO;
import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordService {
    @Autowired
    private MedicalRecordsDAO MedicalRecordsDAO;
    
    @Autowired
    private PersonService personService;

    public List<MedicalRecord> findAll() {
        return MedicalRecordsDAO.getAllMedicalRecords();
    }

    public MedicalRecord findMedicalRecord(String firstName, String lastName) {
        return MedicalRecordsDAO.getMedicalRecords(firstName, lastName);
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        
        if (personService.findPerson(medicalRecord.getFirstName(), medicalRecord.getLastName()) != null) {
            return MedicalRecordsDAO.addMedicalRecords(medicalRecord);
        }

        return null;
    }

    public MedicalRecord update(MedicalRecord medicalRecord) {

        return MedicalRecordsDAO.updateMedicalRecords(medicalRecord);
    }

    public Boolean delete(String firstName, String lastName) {

        return MedicalRecordsDAO.deleteMedicalRecords(firstName, lastName);
    }

}
