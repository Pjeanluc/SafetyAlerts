package com.safetynet.alerts.DAO;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface FireStationDAO {
    public List<FireStation> getAllFireStations();
    public String addFireStation(FireStation fireStation);
    public FireStation updateFireStation(FireStation fireStation);
    public List<FireStation> deleteFireStation(String station, String adresse);
    public void setAllFireStations(List<FireStation> listFireStations);  
    

}
