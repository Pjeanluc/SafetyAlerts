package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DAO.MedicalRecordsDAO;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.web.exceptions.MedicalRecordNotFound;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordController {
    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private MedicalRecordsDAO medicalRecordDAO;
    
    @PostMapping
    public String addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.info("Add medicalrecord : " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());

        return medicalRecordDAO.addMedicalRecords(medicalRecord);

    }

    @PutMapping
    public  MedicalRecord modifyMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        
        MedicalRecord medicalRecordModified = medicalRecordDAO.updateMedicalRecords(medicalRecord);
        
        if (medicalRecordModified == null)
            throw new MedicalRecordNotFound(medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        
        logger.info("modified medicalrecord : " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        return medicalRecordModified;
        
    }

    @DeleteMapping
    public String removeMedicalRecord(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {

        String medicalRecordName = firstName + " " + lastName;
        
        if (medicalRecordDAO.deleteMedicalRecords(firstName, lastName)) {
            logger.info("Delteted medicalrecord : "+ medicalRecordName);
            return "Delteted medicalrecord : "+ medicalRecordName;
        } else throw new MedicalRecordNotFound(medicalRecordName);

    }

    @GetMapping(value = "/all")
    public List<MedicalRecord> listeMedicalRecord() {
        logger.info("Get list medical record");
        return medicalRecordDAO.getAllMedicalRecords();
    }

}
