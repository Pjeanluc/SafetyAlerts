package com.safetynet.alerts.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.services.PersonService;
import com.safetynet.alerts.web.exceptions.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private PersonService personService;

    @PostMapping
    public Person addPerson(@Valid @RequestBody Person person) {

        Person personAdded = personService.save(person);

        if (personAdded == null) {
            logger.error("addPerson : KO");
            throw new PersonAddedException("Add " + person.toString() + " : ERROR");
        }
        logger.info("Add " + personAdded.toString());
        return personAdded;
    }

    @PutMapping
    public Person modifyPerson(@RequestBody Person person) {

        Person personModified = personService.update(person);

        if (personModified == null) {
            logger.error("modifyPerson : Not Found");
            throw new PersonNotFound(person.toString());
        }
        logger.info("modifyPerson : sucess");
        return personModified;
    }

    @DeleteMapping
    public String removePerson(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        if (firstName.isEmpty() || lastName.isEmpty())
            throw new PersonBadParameter("Firstname and lastname are required");

        if (personService.delete(firstName, lastName)) {
            logger.info("Remove person succeeded");
            return "Deleted person : " + firstName + " " + lastName;
        } else
            throw new PersonNotFound(firstName + " " + lastName);

    }

    @GetMapping
    public List<Person> getPerson(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        logger.info("Get person");
        List<Person> persons = personService.findPerson(firstName, lastName);

        if (persons.size() == 0) {
            logger.error("getPerson : Not Found");
            throw new PersonNotFound(firstName + " " + lastName);
        }
        return persons;
    }

}
