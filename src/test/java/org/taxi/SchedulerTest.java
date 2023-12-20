package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taxi.booking.Booking;
import org.taxi.booking.Scheduler;
import org.taxi.map.Location;
import org.taxi.map.GridMap;
import org.taxi.taxi.NormalTaxi;
import org.taxi.taxi.PartyBusTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.TaxiBank;

public class SchedulerTest {
    private GridMap map;
    private Scheduler scheduler;


    @BeforeEach
    void setup() {
        map = new GridMap(5, 5);
        TaxiBank.clear();
        scheduler = new Scheduler(map);
    }

    @Test
    void testCompareDistance() {
        Taxi taxi1 = new Taxi("TAXI1");
        Taxi taxi2 = new Taxi("TAXI2");
        Location location = map.getLocation(1, 1);
        Location taxi1Location = map.getLocation(2, 2);
        Location taxi2Location = map.getLocation(3, 3);

        taxi1.setLocation(map, taxi1Location);
        taxi2.setLocation(map, taxi2Location);

        assertEquals(0, scheduler.compareDistance(taxi1, taxi2, location));
        assertEquals(0, scheduler.compareDistance(taxi2, taxi1, location));
    }

    @Test
    void testEndRide() {
        Taxi taxi = new Taxi("TAXI1");
        Location startLocation = map.getLocation(0, 0);
        Booking booking = new Booking(map, startLocation, map.getLocation(4, 4));
        booking.setTaxi(taxi);

        scheduler.endRide(booking);
        assertNull(booking.getTaxi(), "Taxi should be null after endRide");
    }

    @Test
    void testCancelBooking() {
        Booking booking = new Booking(map, map.getLocation(1, 1), map.getLocation(4, 4));
        scheduler.addBooking(booking, taxi -> true);

        assertTrue(scheduler.getBookings().contains(booking), "Booking should exist before cancellation");
        scheduler.cancelBooking(booking);
        assertFalse(scheduler.getBookings().contains(booking), "Booking should not exist after cancellation");
    }

    @Test
    void testGetBookings() {
        Booking booking1 = new Booking(map, map.getLocation(1, 1), map.getLocation(4, 4));
        Booking booking2 = new Booking(map, map.getLocation(2, 2), map.getLocation(3, 3));
        scheduler.addBooking(booking1, taxi -> true);
        scheduler.addBooking(booking2, taxi -> true);

        assertEquals(2, scheduler.getBookings().size(), "Scheduler should have two bookings");
        assertTrue(scheduler.getBookings().contains(booking1), "Scheduler should contain booking1");
        assertTrue(scheduler.getBookings().contains(booking2), "Scheduler should contain booking2");
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
    void testDetachTaxi() {
        Taxi taxi = new Taxi("TEST");
        scheduler.attach(taxi);
        scheduler.detach(taxi);
        assertFalse(scheduler.getObservers().contains(taxi));
    }

    @Test
    void testNotifyObservers() {
        TaxiBank taxiBank = new TaxiBank();
        Taxi taxi1 = new NormalTaxi("TAXI1");
        Taxi taxi2 = new PartyBusTaxi("TAXI2");
        taxiBank.attachAll(scheduler);
        Location location = map.getLocation(1, 1);
        location.addTaxi(taxi1);
        Booking booking = new Booking(map, location, location);
        
        scheduler.addBooking(booking, taxi -> taxi instanceof NormalTaxi);
        assertFalse(taxi1.isFree(), "Taxi1 should be notified after adding booking");
        assertTrue(taxi2.isFree(), "Taxi2 should be notified after adding booking");

        scheduler.cancelBooking(booking);
        assertFalse(taxi1.isFree(), "Taxi1 should be notified after canceling booking");
        assertTrue(taxi2.isFree(), "Taxi2 should be notified after canceling booking");
    }
}
