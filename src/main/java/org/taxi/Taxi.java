package org.taxi;

public class Taxi {
    private String registrationNumber;
    private boolean isFree;

    Taxi(String registrationNumber){
        this. registrationNumber = registrationNumber;
        this.isFree = true;
    }

    public Location getLocation(Map map){//uses lambdas to get the location of the taxi
        return map.getLocationNodes()
                .stream()
                .filter(l -> l.getContainedTaxis().contains(this))
                .toList()
                .get(0);
    }
}
