package com.safetynet.alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DAO.PersonDAO;
import com.safetynet.alerts.model.Person;


@RestController
public class PersonController {
    
    @Autowired
    private PersonDAO personDAO;
    
    @GetMapping(value="/Persons/{firstName}/{lastName}")
    public Person listePersons(@PathVariable(required = true) String firstName,@PathVariable(required = true) String lastName ) {
       
        return personDAO.personFindByFirstNameAndLastName(firstName, lastName);
    }
    
    @GetMapping(value="/Persons")
    public List<Person> listePersons() {
       
        return personDAO.getAllPersons();
    }

 
}
