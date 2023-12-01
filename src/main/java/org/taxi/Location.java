package org.taxi;
import java.util.List;


public class Location {
    private List<Location> neighbouringLocations;
    private List<Taxi> containedTaxis;
    public Location(List<Location> neighbouringLocations, List<Taxi> containedTaxis) {
        this.neighbouringLocations = neighbouringLocations;
    }
    public List<Location> getNeighbouringLocations() {
        return neighbouringLocations;
    }
    public void setNeighbouringLocations(List<Location> neighbouringLocations) {
        this.neighbouringLocations = neighbouringLocations;
    }
    public List<Taxi> getContainedTaxis() {
        return containedTaxis;
    }
    public void setContainedTaxis(List<Taxi> containedTaxis) {
        this.containedTaxis = containedTaxis;
    }

    
}
