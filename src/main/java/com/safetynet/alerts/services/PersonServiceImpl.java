package com.safetynet.alerts.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.DAO.PersonDAO;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.ChildInfo;
import com.safetynet.alerts.model.url.InfoPerson;
import com.safetynet.alerts.model.url.PhoneInfo;

@Repository
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public List<Person> findAll() {
        return personDAO.getAllPersons();
    }

    @Override
    public List<Person> findPerson(String firstName, String lastName) {
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
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());
            if (medicalRecord != null) {
                int age = serviceUtil.calculateAge(medicalRecord.getBirthdate());

                if (serviceUtil.isChild(age)) {
                    ChildInfo childInfo = new ChildInfo();
                    childInfo.setFirstName(p.getFirstName());
                    childInfo.setLastName(p.getLastName());
                    childInfo.setAge(age);
                    childInfo.setHouseMembers(allPersonInAdress);
                    listChildInfo.add(childInfo);
                }
            }
        }
        return listChildInfo;
    }

    @Override
    public List<PhoneInfo> getListPhoneInfo(String station) {
        List<PhoneInfo> listPhoneInfo = new ArrayList<>();
        List<Person> listPersonFireStation = new ArrayList<>();
        List<Person> listPersonAddress = new ArrayList<>();
        List<String> listAddress = fireStationService.addressCoveredByStation(station);

        for (String a : listAddress) {
            listPersonAddress = findPersonByAddress(a);
            listPersonFireStation.addAll(listPersonAddress);
        }

        PhoneInfo phoneInfo = new PhoneInfo();
        for (Person p : listPersonFireStation) {
            phoneInfo.setFirstName(p.getFirstName());
            phoneInfo.setLastName(p.getLastName());
            phoneInfo.setPhone(p.getPhone());
            listPhoneInfo.add(phoneInfo);
        }

        return listPhoneInfo;
    }

    @Override
    public List<InfoPerson> getPersonInfo(String firstName, String lastName) {
        List<InfoPerson> result = new ArrayList<>();
        List<Person> persons = new ArrayList<>();
        persons = findPerson(firstName, lastName);

        for (Person p : persons) {
            InfoPerson infoPerson = new InfoPerson();
            infoPerson.setFirstName(p.getFirstName());
            infoPerson.setLastName(p.getLastName());
            infoPerson.setEmail(p.getEmail());
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());
            if (medicalRecord != null) {
                infoPerson.setAge(serviceUtil.calculateAge(medicalRecord.getBirthdate()));
                infoPerson.setAllergies(medicalRecord.getAllergies());
                infoPerson.setMedications(medicalRecord.getMedications());
            }
            result.add(infoPerson);
        }
        return result;
    }

}
