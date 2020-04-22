package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.url.FloodHome;
import com.safetynet.alerts.services.FireStationService;

@RestController
public class FloodController {
    private static final Logger logger = LogManager.getLogger("FloodController");
    
    @Autowired
    FireStationService fireStationService;
    
    @GetMapping(value = "/flood")
    public List<FloodHome> getFloodListHome(@RequestParam("stations") List<String> stations) throws Exception {

        if (stations.isEmpty()) {
            logger.info("getFloodListHome : parameter is empty");
            throw new Exception("list of stations is empty");
        }
        logger.info("getFloodListHome sucess");
        return fireStationService.getFloodListHome(stations);

    }

}
