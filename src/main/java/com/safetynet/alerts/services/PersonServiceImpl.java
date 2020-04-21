package com.safetynet.alerts.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.DAO.PersonDAO;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.ChildInfo;

@Repository
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired

    private ServiceUtil serviceUtil;

    @Override
    public List<Person> findAll() {
        return personDAO.getAllPersons();
    }

    @Override
    public Person findPerson(String firstName, String lastName) {
        return personDAO.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Person save(Person person) {

        return personDAO.addPerson(person);
    }

    @Override
    public Person update(Person person) {

        return personDAO.updatePerson(person);
    }

    @Override
    public Boolean delete(String firstName, String lastName) {

        return personDAO.deletePerson(firstName, lastName);
    }

    @Override
    public List<Person> findPersonByAddress(String address) {
        return personDAO.findPersonByAdress(address);
    }

    @Override
    public List<ChildInfo> getListChild(String address) throws Exception {
        List<Person> allPersonInAdress = new ArrayList<>();
        allPersonInAdress = this.findPersonByAddress(address);

        List<ChildInfo> listChildInfo = new ArrayList<>();
        for (Person p : allPersonInAdress) {
            if (medicalRecordService.isChild(p)) {
                ChildInfo childInfo = new ChildInfo();
                childInfo.setFirstName(p.getFirstName());
                childInfo.setLastName(p.getLastName());
                // childInfo.setAge(serviceUtil.calculateAge(date));
                childInfo.setHouseMembers(allPersonInAdress);
                listChildInfo.add(childInfo);
            }
        }
        return listChildInfo;
    }

}
