package org.taxi;

public class Taxi {
    private Location location;
    private String registrationNumber;
    private boolean isFree;

    Taxi(Location location, String registrationNumber){
        this.location = location;
        this. registrationNumber = registrationNumber;
        this.isFree = true;
    }
}
