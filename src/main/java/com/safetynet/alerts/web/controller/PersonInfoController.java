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
import com.safetynet.alerts.services.PersonService;

@RestController
public class PersonInfoController {
    private static final Logger logger = LogManager.getLogger("FireController");

    @Autowired
    PersonService personService;

    @GetMapping(value = "/personInfo")
    public MappingJacksonValue getPersonInfo(@RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) throws Exception {

        if (lastName.isEmpty()) {
            logger.info("getPersonInfo : lastName is empty");
            throw new Exception("lastName must be not empty");
        }
        logger.info("getPersonInfo sucess");

        List<InfoPersonFull> dtoObject = personService.getPersonInfo(firstName, lastName);

        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("phone", "station"));
        resultat.setFilters(filter);
        return resultat;

    }

}
