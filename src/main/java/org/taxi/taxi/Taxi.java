package org.taxi.taxi;

import org.taxi.booking.Booking;
import org.taxi.booking.Observer;
import org.taxi.map.Location;
import org.taxi.map.GridMap;

public class Taxi implements Observer {
    private String registrationNumber;
    private boolean isFree;
    private double rating = 0;
    private int totalRatings = 0;

    public Taxi(String registrationNumber){
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

    // method to update state based on notification 
    public void update (Booking booking) {
        if (booking.getTaxi() != null && booking.getTaxi().equals(this)) {
            // handles both free and assign
            // if booking.getTaxi is null or the taxi is not equal to this taxi. Then isFree is set to true
            this.isFree = booking.getTaxi() == null || !booking.getTaxi().equals(this);
        }
    }

    // get the location of the taxi
    public Location getLocation(GridMap map){//uses lambdas to get the location of the taxi
        try {
            return map.getLocationNodes()
                    .stream()
                    .filter(l -> l.getContainedTaxis().contains(this))
                    .toList()
                    .get(0);    
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }

    public void setLocation(GridMap map, Location newLocation) {
        Location loc = getLocation(map);
        if (loc == null) {
            newLocation.addTaxi(this);
        }
        
        // scenario where you have it in a location already and don't want a taxi at two locations
        else {
            loc.getContainedTaxis().remove(this);
            newLocation.addTaxi(this);
        }
    }

    // getters and setters for rating
    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public double getRating() {
        return rating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    
}
