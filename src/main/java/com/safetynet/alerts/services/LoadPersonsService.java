package com.safetynet.alerts.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.safetynet.alerts.DAO.PersonDAO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.ObjectsSafetyAlert;
import com.safetynet.alerts.model.Person;

@Service
public class LoadPersonsService {
    private static final Logger logger = LogManager.getLogger("LoadPersonsService");

    @Value("${filename}")
    private String fileName;

    @Autowired
    PersonDAO personDao;

    List<Person> listPerson = new ArrayList<Person>();
    List<FireStation> listfireStation = new ArrayList<FireStation>();
    List<MedicalRecord> listmedicalRecord = new ArrayList<MedicalRecord>();

    @PostConstruct
    public void LoadPersons() throws IOException {
               
       // ObjectsSafetyAlert objects = JsonIterator.deserialize(fileName,ObjectsSafetyAlert.class);

        ObjectMapper mapper = new ObjectMapper();

        try {
            ObjectsSafetyAlert object = mapper.readValue(new File(fileName), ObjectsSafetyAlert.class);

            listPerson = object.getPersons();
            listfireStation = object.getFirestations();
            listmedicalRecord = object.getMedicalrecords();

            personDao.setAllPersons(listPerson);
            
            logger.info("Loaded Json objects");

        } catch (JsonMappingException e) {
            e.printStackTrace();
            logger.error("Error Mapping JSON", e);
        } catch (JsonParseException e) {
            e.printStackTrace();
            logger.error("Error parsing JSON", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Error inupt/output JSON", e);
        }
        
    }

}
