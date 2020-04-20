package com.safetynet.alerts.DAO;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.safetynet.alerts.model.Person;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class PersonDAOTest {

    @Autowired
    PersonDAO persondao;

    @Test
    public void saveOnePersonTest() {

        // GIVEN
        Person personTest = new Person("Firstname", "Lastname", "adress", "City", "zip", "phone", "email.email");

        // WHEN
        Person Personadded = persondao.addPerson(personTest);

        // THEN
        assertThat(Personadded.getFirstName()).isEqualTo(personTest.getFirstName());
        assertThat(Personadded.getLastName()).isEqualTo(personTest.getLastName());
        assertThat(Personadded.getAddress()).isEqualTo(personTest.getAddress());
        assertThat(Personadded.getCity()).isEqualTo(personTest.getCity());
        assertThat(Personadded.getZip()).isEqualTo(personTest.getZip());
        assertThat(Personadded.getPhone()).isEqualTo(personTest.getPhone());
        assertThat(Personadded.getEmail()).isEqualTo(personTest.getEmail());
    }

    @Test
    public void updateOneExistingPersonTest() {

        // GIVEN
        Person personTest = new Person("Firstnametest1", "Lastnametest1", "adress", "City", "zip", "phone",
                "email.email");
        // WHEN
        Person personupdated = persondao.updatePerson(personTest);

        // THEN
        assertThat(personupdated.getAddress()).isEqualTo(personTest.getAddress());
        assertThat(personupdated.getCity()).isEqualTo(personTest.getCity());
        assertThat(personupdated.getZip()).isEqualTo(personTest.getZip());
        assertThat(personupdated.getPhone()).isEqualTo(personTest.getPhone());
        assertThat(personupdated.getEmail()).isEqualTo(personTest.getEmail());

    }

    @Test
    public void updateOneNotExistingPersonTest() {

        // GIVEN
        Person personTestNotExist = new Person("Notexist", "Notexist", "adress", "City", "zip", "phone", "email.email");
        // WHEN
        Person personupdated = persondao.updatePerson(personTestNotExist);

        // THEN
        assertThat(personupdated).isNull();

    }

    @Test
    public void deleteOneExistingPersonTest() {

        // GIVEN

        // WHEN

        // THEN
        assertThat(persondao.deletePerson("Firstnametestdelete", "Lastnametestdelete")).isTrue();

    }

    @Test
    public void deleteOneNotExistingPersonTest() {

        // GIVEN
        // WHEN

        // THEN
        assertThat(persondao.deletePerson("Notexistfirstnametest", "Notexistlastnametest")).isFalse();

    }

}
