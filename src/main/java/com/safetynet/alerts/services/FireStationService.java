package com.safetynet.alerts.services;

import java.text.ParseException;
import java.util.List;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.url.FireStationCoverage;

public interface FireStationService {
    
    public List<FireStation> findAll();
    
    public FireStation save(FireStation fireStation);

    public FireStation update(FireStation fireStation);

    public List<FireStation> delete(FireStation fireStation);

    public FireStationCoverage fireStationPersonsCovered(String station) throws ParseException;

}
