package com.safetynet.alerts.services;

import java.util.List;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.ChildInfo;


public interface PersonService {
    public List<Person> findAll();

    public Person findPerson(String firstName, String lastName);

    public Person save(Person person);

    public Person update(Person person);

    public Boolean delete(String firstName, String lastName);

    public List<Person> findPersonByAddress(String address);

    public List<ChildInfo> getListChild(String address) throws Exception;

}
