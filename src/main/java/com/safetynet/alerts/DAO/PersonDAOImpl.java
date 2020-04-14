package com.safetynet.alerts.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
    
    private List<Person> persons =new ArrayList<>();
   

    @Override
    public List<Person> getAllPersons() {
        
        return persons;
    }

    @Override
    public void personSave(Person person) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void personUpdate(Person person) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void personDelete(String firstName, String lastname) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Person personFindByFirstNameAndLastName(String firstName, String lastName) {
                        
        for (Person person:persons) {
                      
            if ((person.getFirstName().equals(firstName)) && (person.getLastName().equals(lastName)) ){
                  
                return person;
            }            
        }
               
        return null;
    }

    @Override
    public List<Person> findByAddress(String adress) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAllPersons(List<Person> listPerson) {
        this.persons = listPerson;
        
    }

}
