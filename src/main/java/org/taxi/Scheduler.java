package org.taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Scheduler {
    private Map map;
    private List<Booking> bookings;

    public static void main(String[] args) {
        Map map = new Map(5,5);
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
    public Scheduler(Map map) {
        this.map = map;
        this.bookings = new ArrayList<>();
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
            // set the taxi isFree to false 
            taxi.setFree(false);
            // assign the taxi to the booking
            booking.setTaxi(taxi);
        });
    }

    private Optional<Taxi> findNearestAvailableTaxi(Location customerLocation) {
        return TaxiBank.getAllTaxis().stream()
                .filter(Taxi::isFree)
                .min((taxi1, taxi2) -> compareDistance(taxi1, taxi2, customerLocation));
    }

    private int compareDistance(Taxi taxi1, Taxi taxi2, Location customerLocation) {
        double distance1 = Dijkstra.calculateDistance(map, taxi1.getLocation(map), customerLocation);
        double distance2 = Dijkstra.calculateDistance(map, taxi2.getLocation(map), customerLocation);
        return Double.compare(distance1, distance2);
    }

    public void freeTaxi(Booking booking) {
        Taxi taxi = booking.getTaxi();
        if (taxi != null) {
            taxi.setFree(true);
            booking.setTaxi(null);
        }
    }

    // getters
    public Map getMap() {
        return map;
    }

    public List<Booking> getBookings() {
        return bookings;
    }   
}
