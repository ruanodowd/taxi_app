package org.taxi.booking;

import org.taxi.taxi.Taxi;
import org.taxi.taxi.TaxiBank;
import org.taxi.datastructure.ArrayList;
import org.taxi.map.Location;
import org.taxi.map.GridMap;
import org.taxi.map.pathfinding.Dijkstra;

import java.util.Optional;

public class Scheduler {
    private GridMap map;
    private ArrayList<Booking> bookings;
    // to keep track of observers
    private ArrayList<Taxi> observers;

    public static void main(String[] args) {
        GridMap map = new GridMap(5,5);
        Scheduler scheduler = new Scheduler(map);
        Booking booking = new Booking(map.getLocation(2, 2));

        Location loc = map.getLocation(0, 0);
        Taxi taxi1 = new Taxi("RAWR");
        loc.addTaxi(taxi1);
        
        Location loc2 = map.getLocation(4, 3);
        Taxi taxi2 = new Taxi("BREE");
        loc2.addTaxi(taxi2);


        scheduler.addBooking(booking);
        System.out.println(booking.getTaxi().getRegistrationNumber());


    }

    // constructor
    public Scheduler(GridMap map) {
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
        freeTaxi(booking);
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
        return TaxiBank.getAllTaxisStream()
                .filter(Taxi::isFree)
                .min((taxi1, taxi2) -> compareDistance(taxi1, taxi2, customerLocation));
    }

    private int compareDistance(Taxi taxi1, Taxi taxi2, Location customerLocation) {
        double distance1 = new Dijkstra().calculateDistance(map, taxi1.getLocation(map), customerLocation);
        double distance2 = new Dijkstra().calculateDistance(map, taxi2.getLocation(map), customerLocation);
        return Double.compare(distance1, distance2);
    }

    public void freeTaxi(Booking booking) {
        Taxi taxi = booking.getTaxi();
        if (taxi != null) {
            booking.setTaxi(null);
            notifyObservers(booking);
        }
    }

    // getters
    public GridMap getMap() {
        return map;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<Taxi> getObservers() {
        return observers;
    }   
}
