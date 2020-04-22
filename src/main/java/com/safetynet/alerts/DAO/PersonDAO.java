package com.safetynet.alerts.DAO;

import java.util.List;

import com.safetynet.alerts.model.Person;

public interface PersonDAO {
      
    public List<Person> getAllPersons();
    public Person addPerson(Person person);
    public Person updatePerson(Person person);
    public Boolean deletePerson(String firstName, String lastname);    
    public List<Person> findPersonByFirstNameAndLastName(String firstName, String lastname); 
    public List<Person> findPersonByAdress(String address);
    public void setAllPersons(List<Person> listPerson);  
    

}
