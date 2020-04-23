package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.url.ChildInfo;
import com.safetynet.alerts.model.url.PhoneInfo;
import com.safetynet.alerts.services.PersonService;

@RestController
public class AlertController {
    private static final Logger logger = LogManager.getLogger("AlertController");
    
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/childAlert")
    public List<ChildInfo> getFireStationCoverage(@RequestParam("address") String address) throws Exception {
        logger.info("childAlert : success");
        
        return personService.getListChild(address);

    }
    
    @GetMapping(value = "/phoneAlert")
    public List<PhoneInfo> getphoneAlertByFireStation(@RequestParam("firestation") String station) throws Exception {
        logger.info("phoneAlert : success");

        return personService.getListPhoneInfo(station);

    }

}
