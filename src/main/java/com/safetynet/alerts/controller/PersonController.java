package com.safetynet.alerts.controller;

import java.net.URI;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.safetynet.alerts.DAO.PersonDAO;
import com.safetynet.alerts.model.Person;

@RestController
public class PersonController {
    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private PersonDAO personDAO;

    @PostMapping
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {

        Person personAdded = personDAO.personAdd(person);

        if (personAdded == null) {
            logger.info("Add person ERROR");
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}/{lastName}")
                .buildAndExpand(personAdded.getFirstName(), personAdded.getLastName()).toUri();
        logger.info("Add person succeeded");
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public String modifyPerson(@RequestBody Person person) {
        return personDAO.personUpdate(person);
    }

       
    @DeleteMapping
    public String removePerson(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            logger.error("Firstname and lastname are not well informed");
            return "FirstName and Lastname are not corrects";
        }
        
        if (personDAO.personDelete(firstName, lastName)) {
            logger.info("Remove person succeeded");
            return "Deleted person : " + firstName + " " + lastName;
        } else {
            logger.error("Remove person : ERROR");
            return "Deleted person : " + firstName + " " + lastName + " : KO";            
        }
    }
    
    @GetMapping
    public Person listePersons(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        logger.info("Get person");
        return personDAO.personFindByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping(value = "/Persons")
    public List<Person> listePersons() {
        logger.info("Get list person");
        return personDAO.getAllPersons();
    }

}
