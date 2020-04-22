package com.safetynet.alerts.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.DAO.FireStationDAO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.url.FireListPerson;
import com.safetynet.alerts.model.url.FireStationCoverage;
import com.safetynet.alerts.model.url.FireStationPerson;
import com.safetynet.alerts.model.url.FloodPerson;
import com.safetynet.alerts.model.url.FloodHome;;

@Repository
public class FireStationServiceImpl implements FireStationService {

    @Autowired
    private FireStationDAO firestationDAO;

    @Autowired
    private PersonService personService;

    @Autowired
    private ServiceUtil serviceUtil;

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
    public String stationNumber(String address) {
        // TODO Auto-generated method stub
        return firestationDAO.getStationByAddress(address);
    }

    @Override
    public List<FireStation> delete(FireStation fireStation) {

        return firestationDAO.deleteFireStation(fireStation);
    }

    @Override
    public List<String> addressCoveredByStation(String station) {
        return firestationDAO.getadressFireStationByStation(station);
    }

    @Override
    public FireStationCoverage fireStationPersonsCovered(String station) throws ParseException {

        FireStationCoverage fireStationCoverage = new FireStationCoverage();
        List<FireStationPerson> fireStationPersons = new ArrayList<>();

        List<Person> personByStation = new ArrayList<>();
        List<Person> personByAddress = new ArrayList<>();

        List<String> adressCoveredByStation = addressCoveredByStation(station);

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
            if (serviceUtil.isChild(p)) {
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

    @Override
    public List<FireListPerson> getFireListPerson(String address) {
        List<FireListPerson> fireStationListPerson = new ArrayList<>();

        List<Person> personByAddress = new ArrayList<>();
        personByAddress = personService.findPersonByAddress(address);

        for (Person p : personByAddress) {
            FireListPerson fireListPerson = new FireListPerson();
            MedicalRecord medicalRecord = new MedicalRecord();

            fireListPerson.setFirstName(p.getFirstName());
            fireListPerson.setLastName(p.getLastName());
            fireListPerson.setPhone(p.getPhone());
            medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());
            if (medicalRecord != null) {
                fireListPerson.setAllergies(medicalRecord.getAllergies());
                fireListPerson.setMedications(medicalRecord.getMedications());
                fireListPerson.setAge(serviceUtil.calculateAge(medicalRecord.getBirthdate()));
            }
            fireListPerson.setStation(stationNumber(address));
            fireStationListPerson.add(fireListPerson);

        }

        return fireStationListPerson;
    }

    @Override
    public List<FloodHome> getFloodListHome(List<String> stations) {

        List<FloodHome> result = new ArrayList<>();
        List<String> adresses = new ArrayList<>();

        for (String s : stations) {
            List<String> adressesForOneFireStation = new ArrayList<>();
            adressesForOneFireStation = addressCoveredByStation(s);
            adresses.addAll(adressesForOneFireStation);
        }

        for (String a : adresses) {
            List<Person> listPersons = new ArrayList<>();
            listPersons = personService.findPersonByAddress(a);

            FloodHome floodHome = new FloodHome();
            floodHome.setAddress(a);

            List<FloodPerson> listFloodPerson = new ArrayList<>();
            for (Person p : listPersons) {
                FloodPerson floodListPersons = new FloodPerson();
                MedicalRecord medicalRecord = new MedicalRecord();
                floodListPersons.setFirstName(p.getFirstName());
                floodListPersons.setLastName(p.getLastName());
                floodListPersons.setPhone(p.getPhone());
                medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());
                if (medicalRecord != null) {
                    floodListPersons.setAllergies(medicalRecord.getAllergies());
                    floodListPersons.setMedications(medicalRecord.getMedications());
                    floodListPersons.setAge(serviceUtil.calculateAge(medicalRecord.getBirthdate()));
                }
                listFloodPerson.add(floodListPersons);
            }
            floodHome.setFloodListPersons(listFloodPerson);
            result.add(floodHome);
        }

        return result;
    }

}
