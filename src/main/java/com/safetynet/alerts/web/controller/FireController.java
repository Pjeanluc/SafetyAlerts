package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.safetynet.alerts.model.url.InfoPersonFull;
import com.safetynet.alerts.services.FireStationService;

@RestController
public class FireController {
    private static final Logger logger = LogManager.getLogger("FireController");

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/fire")
    public MappingJacksonValue getFireListPerson(@RequestParam("address") String address) throws Exception {

        if (address.isEmpty()) {
            logger.error("getFireListPerson : parameter is empty");
            throw new Exception("address value is empty");
        }
        logger.info("getFireListPerson sucess");
        
        List<InfoPersonFull> dtoObject=fireStationService.getFireListPerson(address);
        
        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("email","station","address"));
        resultat.setFilters(filter);
        return resultat;       

    }

}
