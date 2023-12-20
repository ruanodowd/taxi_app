package org.taxi.booking;

import org.taxi.ActualMain;
import org.taxi.map.Map;
import org.taxi.map.pathfinding.Dijkstra;
import org.taxi.pricing.prices.TaxiRate;
import org.taxi.taxi.Taxi;
import org.taxi.map.Location;

public class Booking {
    private final Map map;
    private Taxi allocatedTaxi;
    private Location currentLocation;
    private Location destination;
    private Location[] path;
    private double price;
    private double distance;

    public Booking(Map map, Location currentLocation,
        Location destination) {
        this.map = map;
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.distance = new Dijkstra().calculateDistance(map, currentLocation, destination);
    }
    
    public Location getCustomerLocation() {
        return currentLocation;
    }
    public Location getDestination() {
        return destination;
    }
    public void setTaxi(Taxi taxi) {
        allocatedTaxi = taxi;
    }
    
    public Taxi getTaxi() {
        return allocatedTaxi;
    }
    
    public Map getMap() {
        return map;
    }
    
    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    public void setDestination(Location destination) {
        this.destination = destination;
    }
    
    public Location[] getPath() {
        return path;
    }
    
    public void setPath(Location[] path) {
        this.path = path;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    public void setPrice(TaxiRate rate) {
        ActualMain.priceCalculator.setTaxiRateType(rate);
        this.price = ActualMain.priceCalculator.calculatePrice(distance);
    }
}
