package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.url.InfoPerson;
import com.safetynet.alerts.services.PersonService;

@RestController
public class PersonInfoController {
    private static final Logger logger = LogManager.getLogger("FireController");

    @Autowired
    PersonService personService;

    @GetMapping(value = "/personInfo")
    public List<InfoPerson> getPersonInfo(@RequestParam("firstName") String firstName,
                                          @RequestParam("lastName") String lastName) throws Exception {

        if (lastName.isEmpty()) {
            logger.info("getPersonInfo : lastName is empty");
            throw new Exception("lastName must be not empty");
        }
        logger.info("getPersonInfo sucess");
        return personService.getPersonInfo(firstName, lastName);

    }

}
