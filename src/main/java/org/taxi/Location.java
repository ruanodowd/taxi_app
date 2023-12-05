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
    
    // method to add to list
    public void addTaxi (Taxi taxi) {
        containedTaxis.add(taxi);
    }
    
    public void removeTaxi (Taxi taxi){
        // hopefully this works for the remove functionality, need to test this - U
        containedTaxis.remove(taxi);
    }

    public void locationLink(Location neighbour){
        // my understanding
        // if neighbouringlocations dont contain neighbour
            // add neighbour to the neighbouring locations 
            // add this location to the neighbouring location 

        // concerns - what if this one doesn't have the neighbouring location but another one has it. Then you are adding the this location to the other locations list again - U
        // solution - do a check first before adding it in, I have added that in. - U 
        // Note - might not be essential just a thought. 

        // another change I just call the function (checklocationLink) to check if a location exists in the location's own neighbouringlocation list.
        if (!checklocationLink(neighbour)){
            neighbouringLocations.add(neighbour);
            neighbour.linkNeighbourLocation(this);
        } else {throw new IllegalArgumentException("Nodes already linked");}
    }
    public void linkNeighbourLocation(Location neighbour){
        if (!checklocationLink(neighbour)){
            neighbouringLocations.add(neighbour);
        } else {throw new IllegalArgumentException("Nodes already linked");}
    }
    public boolean checklocationLink(Location neighbour){
        //checks if a location borders another, v useful for testing
        return neighbouringLocations.contains(neighbour);
    }


    public void linkNeighbourLocation(List<Location> locations) {
        locations.forEach(location -> locationLink(location));
    }

}
