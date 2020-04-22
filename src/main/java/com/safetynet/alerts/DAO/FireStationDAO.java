package com.safetynet.alerts.DAO;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

public interface FireStationDAO {
    public List<FireStation> getAllFireStations();
    public FireStation addFireStation(FireStation fireStation);
    public FireStation updateFireStation(FireStation fireStation);
    public List<FireStation> deleteFireStation(FireStation fireStation);
    public List<String> getadressFireStationByStation(String station);
    public String getStationByAddress(String address);
    public void setAllFireStations(List<FireStation> listFireStations);  
    

}
