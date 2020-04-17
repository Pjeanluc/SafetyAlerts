package com.safetynet.alerts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.DAO.FireStationDAO;
import com.safetynet.alerts.model.FireStation;;

@Repository
public class FireStationService {
    
    @Autowired
    private FireStationDAO firestationDAO;

        public List<FireStation> findAll() {
        return firestationDAO.getAllFireStations();
    }
   
    public FireStation save(FireStation fireStation){
      
        return firestationDAO.addFireStation(fireStation);
    }

    
    public FireStation update(FireStation fireStation){
       
        return firestationDAO.updateFireStation(fireStation);
    }
    
       
    public List<FireStation> delete(FireStation fireStation){
      
        return firestationDAO.deleteFireStation(fireStation);
    }


}
