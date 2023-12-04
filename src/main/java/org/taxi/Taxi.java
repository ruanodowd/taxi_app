package org.taxi;

public class Taxi {
    private String registrationNumber;
    private boolean isFree;
    private Location position;

    // when setting up a taxi, you need to have it's original location and reg 
    // this also adds it to the location taxi array 
    // this also adds it to the global taxi bank array
    Taxi(String registrationNumber, Location postion){
        this. registrationNumber = registrationNumber;
        this.isFree = true;
        this.position  = postion;
        position.addTaxi(this);
        TaxiBank.addtoBank(this);
    }
    
    // getters and setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }
    
    // get the location of the taxi
    public Location getLocation(Map map){//uses lambdas to get the location of the taxi
        return map.getLocationNodes()
                .stream()
                .filter(l -> l.getContainedTaxis().contains(this))
                .toList()
                .get(0);
    }

}
