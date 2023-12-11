package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SchedulerTest {
    private Map map;
    private Scheduler scheduler;


    @BeforeEach
    void setup() {
        map = new Map(5, 5);
        TaxiBank.clear();
        scheduler = new Scheduler(map);
    }

    @Test
    void testAddNewBooking() {
        // what I want to do? 
        // test addbooking 
        Booking booking = new Booking(map.getLocation(1, 1));
        scheduler.addBooking(booking);

        // test to see if this adds booking to the list
        assertTrue( scheduler.getBookings().contains(booking), "Booking should be added to the List");
    }

    @Test
    void testAssignTaxi() {
        Location loc = map.getLocation(0, 0);
        Taxi taxi = new Taxi("RAWR");
        loc.addTaxi(taxi);

        Location loc1 = map.getLocation(1, 1);
        Taxi taxi1 = new Taxi("BREE");
        loc1.addTaxi(taxi1);

        Booking booking = new Booking(map.getLocation(1, 1));
        scheduler.addBooking(booking);
        
        assertEquals(taxi1, booking.getTaxi(), "A taxi should be assigned to the booking");
    }

    @Test
    void testNoAvailableTaxi() {
        Booking booking = new Booking(map.getLocation(1, 1)); 
        scheduler.addBooking(booking);

        assertNull(booking.getTaxi(), "No taxi should be assigned if none are available");
    }

    @Test
    void testFreeTaxi() {
        Location loc = map.getLocation(0, 0);
        Taxi taxi = new Taxi("RAWR");
        loc.addTaxi(taxi);
        TaxiBank.addtoBank(taxi);

        Booking booking = new Booking(map.getLocation(1,1)); 
        scheduler.addBooking(booking);
        scheduler.freeTaxi(booking);

        assertTrue(taxi.isFree(), "Taxi should be free after booking is complete");
    }

    @Test
    void testUpdateTaxiLocation() {
        Taxi taxi = new Taxi("RAWR");
        Location originalLocation = map.getLocation(0, 0);
        Location newLocation = map.getLocation(1, 1);
    
        originalLocation.addTaxi(taxi);
    
        taxi.setLocation(map, newLocation);
    
        assertEquals(newLocation, taxi.getLocation(map), "Taxi location should be updated");
    }
    
    @Test
    void testCancelBooking() {
        Location loc = map.getLocation(0, 0);
        Taxi taxi = new Taxi("RAWR");
        loc.addTaxi(taxi);
        TaxiBank.addtoBank(taxi);

        Booking booking = new Booking(map.getLocation(1, 1));
        scheduler.addBooking(booking);
        scheduler.cancelBooking(booking);

        assertFalse(scheduler.getBookings().contains(booking), "Booking should be removed from the scheduler");
        assertTrue(taxi.isFree(), "Taxi should be free after booking cancellation");
    }
}
