package com.safetynet.alerts.model.url;

import java.util.List;

public class FireStationCoverage {
    
    List<FireStationPerson> fireStationPersons;
    int adultCount;
    int childCount;
    
    public List<FireStationPerson> getFireStationPersons() {
        return fireStationPersons;
    }
    public void setFireStationPersons(List<FireStationPerson> fireStationPersons) {
        this.fireStationPersons = fireStationPersons;
    }
    public int getAdultCount() {
        return adultCount;
    }
    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }
    public int getChildCount() {
        return childCount;
    }
    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
    
    

}
