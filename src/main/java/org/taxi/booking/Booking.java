package org.taxi.booking;

import org.taxi.Main;
import org.taxi.map.Map;
import org.taxi.map.pathfinding.Dijkstra;
import org.taxi.pricing.prices.TaxiRate;
import org.taxi.taxi.Taxi;
import org.taxi.map.Location;

public class Booking {
    private final Map map;
    private Taxi allocatedTaxi;
    private final Location currentLocation;
    private final Location destination;
    private double price;
    private final double distance;

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

    public double getPrice() {
        return price;
    }

    public double getDistance() {
        return distance;
    }

    
    public void setPrice(TaxiRate rate) {
        Main.priceCalculator.setTaxiRateType(rate);
        this.price = Main.priceCalculator.calculatePrice(distance);
    }
}
