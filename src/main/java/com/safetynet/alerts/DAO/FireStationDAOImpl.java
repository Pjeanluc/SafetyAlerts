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
    public FireStation addFireStation(FireStation fireStation) {
        fireStations.add(fireStation);
        return fireStation;
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
    public List<FireStation> deleteFireStation(FireStation fireStation) {

        List<FireStation> fireStationsDeleted = new ArrayList<>();

        // delete with address only
        if (fireStation.getStation().isEmpty()) {
            for (FireStation f : fireStations) {
                if (f.getAddress().contentEquals(fireStation.getAddress())) {
                    fireStationsDeleted.add(f);
                }
            }
        }

        // delete with station only
        if (fireStation.getAddress().isEmpty()) {
            for (FireStation f : fireStations) {
                if (f.getStation().contentEquals(fireStation.getStation())) {
                    fireStationsDeleted.add(f);
                }
            }
        }

        // delete with station and address
        if (!fireStation.getAddress().isEmpty() && !fireStation.getStation().isEmpty()) {
            for (FireStation f : fireStations) {
                if ((f.getStation().contentEquals(fireStation.getStation())) && (f.getAddress().contentEquals(fireStation.getAddress()))) {
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
