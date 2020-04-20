package com.safetynet.alerts.DAO;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.safetynet.alerts.model.FireStation;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class FireStationDAOTest {

    @Autowired
    FireStationDAO fireStationDAO;

    @Test
    public void saveOneFireStationTest() {

        // GIVEN
        FireStation fireStationTest = new FireStation("Adressadd", "stationadd");

        // WHEN
        FireStation fireStationAddedd = fireStationDAO.addFireStation(fireStationTest);

        // THEN
        assertThat(fireStationAddedd.getAddress()).isEqualTo(fireStationTest.getAddress());
        assertThat(fireStationAddedd.getStation()).isEqualTo(fireStationTest.getStation());

    }

    @Test
    public void updateOneExistingFireStationTest() {

        // GIVEN
        FireStation fireStationTest = new FireStation("adresstest1", "3");
        // WHEN
        FireStation fireStationUpdated = fireStationDAO.updateFireStation(fireStationTest);

        // THEN
        assertThat(fireStationUpdated.getAddress()).isEqualTo(fireStationTest.getAddress());
        assertThat(fireStationUpdated.getStation()).isEqualTo(fireStationTest.getStation());

    }

    @Test
    public void updateOneNotExistingFireStationTest() {

        // GIVEN
        FireStation fireStationTestNotExist = new FireStation("NotExist", "5");
        // WHEN
        FireStation fireStationUpdated = fireStationDAO.updateFireStation(fireStationTestNotExist);

        // THEN
        assertThat(fireStationUpdated).isNull();

    }

    @Test
    public void deleteOneExistingFireStationByAdressTest() {

        // GIVEN
        FireStation fireStationDelete = new FireStation("delete", "");
        
        // WHEN

        // THEN
        assertThat(fireStationDAO.deleteFireStation(fireStationDelete).size()).isEqualTo(1);

    }

    @Test
    public void deleteOneExistingFireStationByStationTest() {

        // GIVEN
        FireStation fireStationDelete = new FireStation("", "delete");

        // WHEN

        // THEN
        assertThat(fireStationDAO.deleteFireStation(fireStationDelete).size()).isEqualTo(1);

    }

    @Test
    public void deleteOneExistingFireStationByAdressAndStationTest() {

        // GIVEN
        FireStation fireStationDelete = new FireStation("delete2", "delete2");

        // WHEN

        // THEN
        assertThat(fireStationDAO.deleteFireStation(fireStationDelete).size()).isEqualTo(1);

    }

}
