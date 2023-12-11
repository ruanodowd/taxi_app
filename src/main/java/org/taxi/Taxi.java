package org.taxi;

public class Taxi {
    private String registrationNumber;
    private boolean isFree;

    Taxi(String registrationNumber){
        this. registrationNumber = registrationNumber;
        this.isFree = true;
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

    
    // get the location of the taxi
    public Location getLocation(Map map){//uses lambdas to get the location of the taxi
        try {
            return map.getLocationNodes()
                    .stream()
                    .filter(l -> l.getContainedTaxis().contains(this))
                    .toList()
                    .get(0);    
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void setLocation(Map map, Location newLocation) {
        Location loc = getLocation(map);
        loc.getContainedTaxis().remove(this);

        newLocation.addTaxi(this);
    }

}
