package com.safetynet.alerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.DAO.PersonDAO;
import com.safetynet.alerts.model.Person;

@Repository
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

        public List<Person> findAll() {
        return personDAO.getAllPersons();
    }
       
        public Person findPerson(String firstName, String lastName) {
            return personDAO.findPersonByFirstNameAndLastName(firstName, lastName);
        }
   
    public Person save(Person person){
      
        return personDAO.addPerson(person);
    }

    
    public Person update(Person person){
       
        return personDAO.updatePerson(person);
    }
    
       
    public Boolean delete(String firstName, String lastName){
      
        return personDAO.deletePerson(firstName, lastName);
    }

}
