package com.safetynet.alerts.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private List<Person> persons = new ArrayList<>();

    @Override
    public List<Person> getAllPersons() {

        return persons;
    }

    @Override
    public Person addPerson(Person person) {
        persons.add(person);
        return person;

    }

    @Override
    public Person updatePerson(Person person) {
        int index = 0;

        for (Person p : persons) {
            if (p.getFirstName().contentEquals(person.getFirstName())
                    && p.getLastName().contentEquals(person.getLastName())) {
                persons.set(index, person);
                return person;
            }
            index++;
        }

        return null;
    }

    @Override
    public Boolean deletePerson(String firstName, String lastName) {

        for (Person p : persons) {
            if (p.getFirstName().contentEquals(firstName) && p.getLastName().contentEquals(lastName)) {
                persons.remove(p);
                return true;
            }
        }

        return false;

    }

    @Override
    public Person findPersonByFirstNameAndLastName(String firstName, String lastName) {

        for (Person person : persons) {

            if ((person.getFirstName().equals(firstName)) && (person.getLastName().equals(lastName))) {

                return person;
            }
        }

        return null;
    }

       @Override
    public void setAllPersons(List<Person> listPerson) {
        this.persons = listPerson;

    }

}
