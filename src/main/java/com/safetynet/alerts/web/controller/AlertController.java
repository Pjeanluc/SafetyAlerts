package com.safetynet.alerts.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.url.ChildInfo;
import com.safetynet.alerts.services.PersonService;

@RestController
public class AlertController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/childAlert")
    public List<ChildInfo> getFireStationCoverage(@RequestParam("address") String address) throws Exception {
        return personService.getListChild(address);

    }

}
