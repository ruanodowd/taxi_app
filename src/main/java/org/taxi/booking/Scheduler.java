package org.taxi.booking;

import org.taxi.map.Map;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.TaxiBank;
import org.taxi.datastructure.ArrayList;
import org.taxi.map.Location;
import org.taxi.map.pathfinding.Dijkstra;

import java.util.Optional;

public class Scheduler {
    private Map map;
    private ArrayList<Booking> bookings;
    // to keep track of observers
    private ArrayList<Taxi> observers;

    // constructor
    public Scheduler(Map map) {
        this.map = map;
        this.bookings = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    // attach taxi 
    public void attach(Taxi taxi) {
        observers.add(taxi);
    }

    // detach a taxi 
    public void detach(Taxi taxi) {
        observers.remove(taxi);
    }

    // notify observers for a change 
    private void notifyObservers(Booking booking) {
        for (Taxi taxi : observers) {
            taxi.update(booking);
        }
    }

    // to add a booking
    public void addBooking(Booking booking) {
        bookings.add(booking);
        assignTaxiToBooking(booking);
    }

    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
        endRide(booking);
    }

    private void assignTaxiToBooking(Booking booking) {
        // get customer location 
        Location customerLocation = booking.getCustomerLocation();
        // find nearest taxi
        Optional<Taxi> assignedTaxi = findNearestAvailableTaxi(customerLocation);
        
        // if assignedTaxi is present 

        assignedTaxi.ifPresent(taxi -> {
            booking.setTaxi(taxi);
            notifyObservers(booking);
        });
    }

    private Optional<Taxi> findNearestAvailableTaxi(Location customerLocation) {
        new Dijkstra().calculateRoute(map, customerLocation);
        return TaxiBank.getAllTaxisStream()
                .filter(Taxi::isFree)
                .min((taxi1, taxi2) -> compareDistance(taxi1, taxi2, customerLocation));
    }

    private int compareDistance(Taxi taxi1, Taxi taxi2, Location customerLocation) {
        double distance1 = taxi1.getLocation(map).getDistance();
        double distance2 = taxi2.getLocation(map).getDistance();
        return Double.compare(distance1, distance2);
    }

    public void endRide(Booking booking) {
        Taxi taxi = booking.getTaxi();
        if (taxi != null) {
            booking.setTaxi(null);
            notifyObservers(booking);
        }
        //remove completed booking from bookings
    }

    // getters
    public Map getMap() {
        return map;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<Taxi> getObservers() {
        return observers;
    }   
}
