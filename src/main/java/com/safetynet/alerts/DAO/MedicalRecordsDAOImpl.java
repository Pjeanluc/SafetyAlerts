package com.safetynet.alerts.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordsDAOImpl implements MedicalRecordsDAO {
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    
    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }

    @Override
    public String addMedicalRecords(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        return "Added medicalrecord :" + medicalRecord.getFirstName() + "" + medicalRecord.getLastName();
    }

    @Override
    public MedicalRecord updateMedicalRecords(MedicalRecord medicalRecord) {
        int index = 0;

        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().contentEquals(medicalRecord.getFirstName())
                    && m.getLastName().contentEquals(medicalRecord.getLastName())) {
                medicalRecords.set(index, medicalRecord);
                return medicalRecord;
            }
            index++;
        }

        return null;
    }

    @Override
    public Boolean deleteMedicalRecords(String firstName, String lastName) {
        for (MedicalRecord m : medicalRecords) {
            if (m.getFirstName().contentEquals(firstName) && m.getLastName().contentEquals(lastName)) {
                medicalRecords.remove(m);
                return true;
            }
        }

        return false;
    }

    @Override
    public void setAllMedicalRecords(List<MedicalRecord> listmedicalRecord) {
       this.medicalRecords = listmedicalRecord;
        
    }

}
