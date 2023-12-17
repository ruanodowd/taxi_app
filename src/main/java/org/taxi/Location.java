package org.taxi;

public class Location {
    // attributes
    private int x;
    private int y;
    private ArrayList<Location> neighbouringLocations = new ArrayList<>();
    private ArrayList<Taxi> containedTaxis = new ArrayList<>();
    private ArrayList<Location> pathway = new ArrayList<>();

    private Integer distance = Integer.MAX_VALUE;


    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
    public ArrayList<Location> getPathway() {
        return pathway;
    }

    public void setPathway(ArrayList<Location> pathway) {
        this.pathway = pathway;
    }

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
    
    public ArrayList<Location> getNeighbouringLocations() {
        return neighbouringLocations;
    }

    public ArrayList<Taxi> getContainedTaxis() {
        return containedTaxis;
    }
    
    // method to add to list
    public void addTaxi (Taxi taxi) {
        containedTaxis.add(taxi);
    }
    
    public void removeTaxi (Taxi taxi){
        containedTaxis.remove(taxi);
    }

    public void locationLink(Location neighbour){
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


    public void linkNeighbourLocation(ArrayList<Location> locations) {
        locations.forEach(location -> locationLink(location));
    }

}
