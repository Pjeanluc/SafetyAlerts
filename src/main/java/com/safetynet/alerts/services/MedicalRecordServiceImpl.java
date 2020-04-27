package com.safetynet.alerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.DAO.MedicalRecordsDAO;
import com.safetynet.alerts.model.MedicalRecord;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    @Autowired
    private MedicalRecordsDAO MedicalRecordsDAO;

    @Autowired
    private PersonService personService;

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

}
