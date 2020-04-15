package com.safetynet.alerts.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.DAO.FireStationDAO;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.web.exceptions.FireStationNotFound;

@RestController
@RequestMapping("/firestation")
public class FireStationController {
    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private FireStationDAO fireStationDAO;

    @PostMapping
    public String addFireStation(@RequestBody FireStation fireStation) {
        logger.info("Add firestation : " + fireStation.getAddress() + " " + fireStation.getStation());

        return fireStationDAO.addFireStation(fireStation);

    }

    @PutMapping
    public FireStation modifyFireStation(@RequestBody FireStation fireStation) {
        
        FireStation fireStationModified = fireStationDAO.updateFireStation(fireStation);
        
        if (fireStationModified != null) {
            logger.info("Modified firestation : " + fireStationModified);
            return fireStationModified;
        } else throw new FireStationNotFound(fireStation.getAddress() +" "+fireStation.getStation());
    }

    @DeleteMapping
    public List<FireStation> removeFireStation(@RequestParam("address") String adress, @RequestParam("station") String station) {
        
        List<FireStation> fireStationDeleted = fireStationDAO.deleteFireStation(station, adress);
        
        if (fireStationDeleted.size() != 0) {
            logger.info("Deleted firestation : " + fireStationDeleted);
            return fireStationDeleted;
        } else throw new FireStationNotFound(adress +" "+station);

    }

    @GetMapping(value = "/all")
    public List<FireStation> listeFireStaion() {
        logger.info("Get list fireStation");
        return fireStationDAO.getAllFireStations();
    }

}
