package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.url.FireListPerson;
import com.safetynet.alerts.services.FireStationService;

@RestController
public class FireController {
    private static final Logger logger = LogManager.getLogger("FireController");

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/fire")
    public List<FireListPerson> getFireListPerson(@RequestParam("address") String address) throws Exception {

        if (address.isEmpty()) {
            logger.info("getFireListPerson : parameter is empty");
            throw new Exception("address value is empty");
        }
        logger.info("getFireListPerson sucess");
        return fireStationService.getFireListPerson(address);

    }

}
