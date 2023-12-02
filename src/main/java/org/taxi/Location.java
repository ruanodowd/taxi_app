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
    public Location(){
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
    public void removeTaxi (Taxi taxi){

    }
    public void locationLink(Location neighbour){
        if (!neighbouringLocations.contains(neighbour)){
            neighbouringLocations.add(neighbour);
            neighbour.linkNeighbourLocation(this);
        }
    }
    public void linkNeighbourLocation(Location neighbour){
        neighbouringLocations.add(neighbour);
    }
    public boolean checklocationLink(Location neighbour){
        return neighbouringLocations.contains(neighbour);
    }
    // method to generate the neighbouring nodes


    public void linkNeighbourLocation(List<Location> locations) {
        locations.forEach(location -> locationLink(location));
    }

}
