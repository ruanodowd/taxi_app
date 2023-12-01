package org.taxi;
import java.util.ArrayList;
import java.util.List;


public class Location {
    // attributes
    private int x;
    private int y;
    private List<Location> neighbouringLocations = new ArrayList<>();
    private List<Taxi> containedTaxis = new ArrayList<>();
    
    // constructor for location
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // getters
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }    
    
    public List<Location> getNeighbouringLocations() {
        return neighbouringLocations;
    }

    public List<Taxi> getContainedTaxis() {
        return containedTaxis;
    }
    
    // method to add to lsit
    public void addTaxi (Taxi taxi) {
        containedTaxis.add(taxi);
    }

    // method to generate the neighbouring nodes
    public void addNeighbourLocations(List<Location> locations) {
        for (Location location: locations) {
            neighbouringLocations.add(location);
        }
    }

}
