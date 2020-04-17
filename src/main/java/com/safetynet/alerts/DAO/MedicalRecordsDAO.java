package com.safetynet.alerts.DAO;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface MedicalRecordsDAO {
    public List<MedicalRecord> getAllMedicalRecords();
    public MedicalRecord getMedicalRecords(String firstName, String lastName);
    public MedicalRecord addMedicalRecords(MedicalRecord medicalRecord);
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord);
    public Boolean deleteMedicalRecords(String firstName, String lastName);
    public void setAllMedicalRecords(List<MedicalRecord> listmedicalRecord);  

}
