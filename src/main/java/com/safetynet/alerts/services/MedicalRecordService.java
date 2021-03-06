package com.safetynet.alerts.services;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

public interface MedicalRecordService {
    public List<MedicalRecord> findAll();

    public MedicalRecord findMedicalRecord(String firstName, String lastName);

    public MedicalRecord save(MedicalRecord medicalRecord);

    public MedicalRecord update(MedicalRecord medicalRecord);

    public Boolean delete(String firstName, String lastName);

}
