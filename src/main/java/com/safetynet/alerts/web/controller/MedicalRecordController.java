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

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.services.MedicalRecordService;
import com.safetynet.alerts.web.exceptions.MedicalRecordNotFound;
import com.safetynet.alerts.web.exceptions.MedicalRecordPersonNotFound;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordController {
    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        
        MedicalRecord medicalRecordAdded = medicalRecordService.save(medicalRecord);
        if (medicalRecordAdded == null)
            throw new MedicalRecordPersonNotFound(medicalRecord.getFirstName() + " " + medicalRecord.getLastName());

        logger.info("created medicalrecord : " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        return medicalRecordAdded;

    }

    @PutMapping
    public MedicalRecord modifyMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        MedicalRecord medicalRecordModified = medicalRecordService.update(medicalRecord);

        if (medicalRecordModified == null)
            throw new MedicalRecordNotFound(medicalRecord.getFirstName() + " " + medicalRecord.getLastName());

        logger.info("modified medicalrecord : " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        return medicalRecordModified;

    }

    @DeleteMapping
    public String removeMedicalRecord(@RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName) {

        String medicalRecordName = firstName + " " + lastName;

        if (medicalRecordService.delete(firstName, lastName)) {
            logger.info("Deleteted medicalrecord : " + medicalRecordName);
            return "Deleteted medicalrecord : " + medicalRecordName;
        } else
            throw new MedicalRecordNotFound(medicalRecordName);

    }

    @GetMapping
    public MedicalRecord getMedicalRecord(@RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName) {

        if (medicalRecordService.findMedicalRecord(firstName, lastName) != null) {
            logger.info("Get record");
            return medicalRecordService.findMedicalRecord(firstName, lastName);
        } else
            throw new MedicalRecordNotFound(firstName + " " + lastName);

    }

    @GetMapping(value = "/all")
    public List<MedicalRecord> listeMedicalRecord() {
        logger.info("Get list medical record");
        return medicalRecordService.findAll();
    }

}
