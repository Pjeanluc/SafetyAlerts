package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.url.CommunityEmail;
import com.safetynet.alerts.services.PersonService;

@RestController
public class CommunityEmailController {
    private static final Logger logger = LogManager.getLogger("CommunityEmailController");
    
    @Autowired
    PersonService personService;

    @GetMapping(value = "/communityEmail")
    public List<CommunityEmail> getCommunityEmail(@RequestParam("city") String city) throws Exception {

        if (city.isEmpty()) {
            logger.info("getCommunityEmail : city is empty");
            throw new Exception("city value is empty");
        }
        logger.info("getCommunityEmail sucess");
        return personService.getCommunityEmail(city);

    }

}
