package com.safetynet.alerts.DAO;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface PersonDAO {
      
    public List<Person> getAllPersons();
    public Person personAdd(Person person);
    public String personUpdate(Person person);
    public Boolean personDelete(String firstName, String lastname);
    
    public Person personFindByFirstNameAndLastName(String firstName, String lastname);
    public List<Person> findByAddress(String adress);
    public void setAllPersons(List<Person> listPerson);  
    

}
