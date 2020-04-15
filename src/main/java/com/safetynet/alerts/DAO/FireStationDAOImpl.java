package com.safetynet.alerts.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.FireStation;

@Repository
public class FireStationDAOImpl implements FireStationDAO {
    private List<FireStation> fireStations = new ArrayList<>();

    @Override
    public List<FireStation> getAllFireStations() {

        return fireStations;
    }

    @Override
    public String addFireStation(FireStation fireStation) {
        fireStations.add(fireStation);
        return "firestation added : " + fireStation;
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation) {
        int index = 0;

        for (FireStation f : fireStations) {
            if (f.getAddress().contentEquals(fireStation.getAddress())) {
                fireStations.set(index, fireStation);
                return fireStation;
            }
            index++;
        }

        return null;

    }

    @Override
    public List<FireStation> deleteFireStation(String station, String address) {

        List<FireStation> fireStationsDeleted = new ArrayList<>();

        // delete with address only
        if (station.isEmpty()) {
            for (FireStation f : fireStations) {
                if (f.getAddress().contentEquals(address)) {
                    fireStationsDeleted.add(f);
                }
            }
        }

        // delete with station only
        if (address.isEmpty()) {
            for (FireStation f : fireStations) {
                if (f.getStation().contentEquals(station)) {
                    fireStationsDeleted.add(f);
                }
            }
        }

        // delete with station and address
        if (!address.isEmpty() && !station.isEmpty()) {
            for (FireStation f : fireStations) {
                if ((f.getStation().contentEquals(station)) && (f.getAddress().contentEquals(address))) {
                    fireStationsDeleted.add(f);
                }
            }
        }

        if (fireStationsDeleted.size() != 0)
            fireStations.removeAll(fireStationsDeleted);

        return fireStationsDeleted;
    }

    @Override
    public void setAllFireStations(List<FireStation> listFireStations) {
        this.fireStations = listFireStations;

    }

}
