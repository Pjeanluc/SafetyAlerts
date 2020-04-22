package com.safetynet.alerts.model.url;

import java.util.List;

public class FloodHome {
    private String address;
    List<FloodPerson> FloodListPersons;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<FloodPerson> getFloodListPersons() {
        return FloodListPersons;
    }
    public void setFloodListPersons(List<FloodPerson> floodListPersons) {
        FloodListPersons = floodListPersons;
    }
    

}
