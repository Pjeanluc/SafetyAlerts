package com.safetynet.alerts.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.services.PersonService;

@RestController
public class HealthController implements HealthIndicator{
    
    @Autowired
    PersonService personService;
    
    @Override
    public Health health() {
        List<Person> persons = personService.findAll();

        if(persons.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
        }    
}
