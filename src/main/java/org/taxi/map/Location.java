package org.taxi.map;

import org.taxi.datastructure.ArrayList;
import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.taxi.Taxi;

public class Location implements Comparable<Location>{
    // co-ordinates
    private int x;
    private int y;
    
    // has the location been visited? 
    private boolean visited = false;
    
    //neighbouring locations
    private ArrayList<Location> neighbouringLocations = new ArrayList<>();
    
    // taxis contained 
    private ArrayList<Taxi> containedTaxis = new ArrayList<>();
    
    // storing the path between the start and the location
    private DoublyLinkedList<Location> pathway = new DoublyLinkedList<>();
    
    // Distance from start location to this location 
    private Integer distance = Integer.MAX_VALUE;
    
    // constructors for location
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location(){
    }

    // getters and setters
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public DoublyLinkedList<Location> getPathway() {
        return pathway;
    }

    public void setPathway(DoublyLinkedList<Location> pathway) {
        this.pathway = pathway;
    }
    
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

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    // method to add to list
    public void addTaxi (Taxi taxi) {
        containedTaxis.add(taxi);
    }
    
    // method to remove taxi 
    public void removeTaxi (Taxi taxi){
        containedTaxis.remove(taxi);
    }

    // methods to link all the taxis from the neighbouring locations 
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

    @Override
    public int compareTo(Location other) {
        return this.distance.compareTo(other.distance);
    }

}
