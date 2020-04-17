package com.safetynet.alerts.DAO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.safetynet.alerts.model.Person;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class PersonDAOTest {
    
    @Autowired
    PersonDAO persondao;
   
    
    @Test
    public void saveOnePersonTest() {

        // GIVEN
        Person personTest = new Person("firstname", "lastname","adress","city","zip","phone","email");
        
        // WHEN        
        Person Personadded = persondao.addPerson(personTest);        
        List<Person> listPersons = persondao.getAllPersons();
        
      // THEN
        assertThat(listPersons.size()).isEqualTo(1) ;        
        assertThat(listPersons.get(0).getFirstName()).isEqualTo(personTest.getFirstName());
    }

}
