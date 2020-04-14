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
    public Person personAdd(Person person) {
        persons.add(person);
        return person;

    }

    @Override
    public String personUpdate(Person person) {
        int index = 0;
        Boolean findedPerson = false;

        for (Person p : persons) {
            if (p.getFirstName().contentEquals(person.getFirstName())
                    && p.getLastName().contentEquals(person.getLastName())) {
                persons.set(index, person);
                findedPerson = true;
            }
            index++;
        }

        if (findedPerson) {
            return "Modified person : " + person.getFirstName() + " " + person.getLastName();
        } else {
            return "Modified person : " + person.getFirstName() + " " + person.getLastName() + " : KO";
        }
    }

    @Override
    public Boolean personDelete(String firstName, String lastName) {

        Boolean findedPerson = false;

        for (Person p : persons) {
            if (p.getFirstName().contentEquals(firstName) && p.getLastName().contentEquals(lastName)) {
                findedPerson = true;
            }
        }

        if (findedPerson) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Person personFindByFirstNameAndLastName(String firstName, String lastName) {

        for (Person person : persons) {

            if ((person.getFirstName().equals(firstName)) && (person.getLastName().equals(lastName))) {

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
