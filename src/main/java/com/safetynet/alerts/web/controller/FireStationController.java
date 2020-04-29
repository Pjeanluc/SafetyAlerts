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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.services.FireStationService;
import com.safetynet.alerts.web.exceptions.FireStationNotFound;

@RestController
@RequestMapping("/firestation")
public class FireStationController {
    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private FireStationService fireStationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        logger.info("Add firestation : " + fireStation.toString());

        return fireStationService.save(fireStation);

    }

    @PutMapping
    public FireStation modifyFireStation(@RequestBody FireStation fireStation) {

        FireStation fireStationModified = fireStationService.update(fireStation);

        if (fireStationModified != null) {
            logger.info("Modified firestation : " + fireStationModified.toString());
            return fireStationModified;
        } else {
            logger.error("Modified firestation : KO");        
            throw new FireStationNotFound(fireStation.toString());
        }
    }

    @DeleteMapping
    public List<FireStation> removeFireStation(@RequestBody FireStation fireStation) {

        List<FireStation> fireStationDeleted = fireStationService.delete(fireStation);

        if (fireStationDeleted.size() != 0) {
            logger.info("Deleted firestation : " + fireStationDeleted.toString());
            return fireStationDeleted;
        } else{
            logger.error("removeFireStation : KO");
            throw new FireStationNotFound(fireStation.toString());
        }
    }
    
   @GetMapping    
   public MappingJacksonValue getFireStationCoverage(@RequestParam("stationNumber") String station) throws Exception {
       logger.info("getFireStationCoverage sucess");
       
       
       FireStationCoverage dtoObject=fireStationService.fireStationPersonsCovered(station);
       
       MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
       FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
               SimpleBeanPropertyFilter.serializeAllExcept("email","age","medications","allergies","station"));
       resultat.setFilters(filter);
       return resultat;
       
       
        
    }
}
