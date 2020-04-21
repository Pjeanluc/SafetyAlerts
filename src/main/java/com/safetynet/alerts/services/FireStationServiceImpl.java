package com.safetynet.alerts.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.DAO.FireStationDAO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.model.url.FireStationPerson;;

@Repository
public class FireStationServiceImpl implements FireStationService {

    @Autowired
    private FireStationDAO firestationDAO;

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Override
    public List<FireStation> findAll() {
        return firestationDAO.getAllFireStations();
    }

    @Override
    public FireStation save(FireStation fireStation) {

        return firestationDAO.addFireStation(fireStation);
    }

    @Override
    public FireStation update(FireStation fireStation) {

        return firestationDAO.updateFireStation(fireStation);
    }

    @Override
    public List<FireStation> delete(FireStation fireStation) {

        return firestationDAO.deleteFireStation(fireStation);
    }

    @Override
    public FireStationCoverage fireStationPersonsCovered(String station) throws ParseException {

        FireStationCoverage fireStationCoverage = new FireStationCoverage();
        List<FireStationPerson> fireStationPersons = new ArrayList<>();

        List<Person> personByStation = new ArrayList<>();
        List<Person> personByAddress = new ArrayList<>();

        List<String> adressCoveredByStation = firestationDAO.getadressFireStationByStation(station);

        int countChild = 0;
        int countAdult = 0;

        // list of person
        for (String a : adressCoveredByStation) {
            personByAddress = personService.findPersonByAddress(a);
            personByStation.addAll(personByAddress);
        }

        for (Person p : personByStation) {
            FireStationPerson fireStationPerson = new FireStationPerson();
            fireStationPerson.setFistName(p.getFirstName());
            fireStationPerson.setLastName(p.getLastName());
            fireStationPerson.setAddress(p.getAddress());
            fireStationPerson.setPhone(p.getPhone());
            if (medicalRecordService.isChild(p)) {
                countChild++;
            } else
                countAdult++;

            fireStationPersons.add(fireStationPerson);
        }

        fireStationCoverage.setFireStationPersons(fireStationPersons);
        fireStationCoverage.setAdultCount(countAdult);
        fireStationCoverage.setChildCount(countChild);

        return fireStationCoverage;

    }

}
