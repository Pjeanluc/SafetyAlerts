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
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.model.url.FloodHome;
import com.safetynet.alerts.services.FireStationService;

@RestController
public class FloodController {
    private static final Logger logger = LogManager.getLogger("FloodController");

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/flood")
    public MappingJacksonValue getFloodListHome(@RequestParam("stations") List<String> stations) throws Exception {

        if (stations.isEmpty()) {
            logger.info("getFloodListHome : parameter is empty");
            throw new Exception("list of stations is empty");
        }
        logger.info("getFloodListHome sucess");

        List<FloodHome> dtoObject = fireStationService.getFloodListHome(stations);

        MappingJacksonValue resultat = new MappingJacksonValue(dtoObject);
        FilterProvider filter = new SimpleFilterProvider().addFilter("filtreInformationPersonFull",
                SimpleBeanPropertyFilter.serializeAllExcept("email", "age", "station"));
        resultat.setFilters(filter);
        return resultat;

    }

}
