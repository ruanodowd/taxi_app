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
import org.taxi.pricing.prices.PartyBusRate;
import org.taxi.taxi.PartyBusTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.TaxiBank;

import java.util.function.Predicate;

public class SchedulerTest {
    private GridMap map;
    private Scheduler scheduler;


    @BeforeEach
    void setup() {
        map = new GridMap(5, 5);
        TaxiBank.clear();
        scheduler = new Scheduler(map);
    }

//    @Test
//    void testAddNewBooking() {
//        // what I want to do?
//        // test addbooking
//        Booking booking = new Booking(map, map.getLocation(1, 1), map.getLocation(9,9));
//        scheduler.addBooking(booking, taxiType -> taxiType instanceof PartyBusTaxi);
//
//        // test to see if this adds booking to the list
//        assertTrue( scheduler.getBookings().contains(booking), "Booking should be added to the List");
//    }

//    @Test
//    void testAssignTaxi() {
//        Location loc = map.getLocation(0, 0);
//        Taxi taxi = new Taxi("RAWR");
//        loc.addTaxi(taxi);
//
//        Location loc1 = map.getLocation(1, 1);
//        Taxi taxi1 = new Taxi("BREE");
//        loc1.addTaxi(taxi1);
//
//        Booking booking = new Booking(map, map.getLocation(1, 1),map.getLocation(6,6));
//        scheduler.addBooking(booking, taxiType -> taxiType instanceof PartyBusTaxi);
//
//        assertEquals(taxi1, booking.getTaxi(), "A taxi should be assigned to the booking");
//    }

//    @Test
//    void testNoAvailableTaxi() {
//        Booking booking = new Booking(map.getLocation(1, 1));
//        scheduler.addBooking(booking);
//
//        assertNull(booking.getTaxi(), "No taxi should be assigned if none are available");
//    }

//    @Test
//    void testFreeTaxi() {
//        Location loc = map.getLocation(0, 0);
//        Taxi taxi = new Taxi("RAWR");
//        loc.addTaxi(taxi);
//        TaxiBank.addtoBank(taxi);
//
//        Booking booking = new Booking(map.getLocation(1,1));
//        scheduler.addBooking(booking);
//        scheduler.freeTaxi(booking);
//
//        assertTrue(taxi.isFree(), "Taxi should be free after booking is complete");
//    }

    @Test
    void testUpdateTaxiLocation() {
        Taxi taxi = new Taxi("RAWR");
        Location originalLocation = map.getLocation(0, 0);
        Location newLocation = map.getLocation(1, 1);

        originalLocation.addTaxi(taxi);

        taxi.setLocation(map, newLocation);

        assertEquals(newLocation, taxi.getLocation(map), "Taxi location should be updated");
    }

//    @Test
//    void testCancelBooking() {
//        Location loc = map.getLocation(0, 0);
//        Taxi taxi = new Taxi("RAWR");
//        loc.addTaxi(taxi);
//        TaxiBank.addtoBank(taxi);
//
//        Booking booking = new Booking(map.getLocation(1, 1));
//        scheduler.addBooking(booking);
//        scheduler.cancelBooking(booking);
//
//        assertFalse(scheduler.getBookings().contains(booking), "Booking should be removed from the scheduler");
//        assertTrue(taxi.isFree(), "Taxi should be free after booking cancellation");
//    }

    @Test
    void testDetachTaxi() {
        Taxi taxi = new Taxi("TEST");
        scheduler.attach(taxi);
        scheduler.detach(taxi);
        assertFalse(scheduler.getObservers().contains(taxi));
    }

//    @Test
//    void testObserverNotification() {
//        Taxi taxi1 = new Taxi("RAWR");
//        Taxi taxi2 = new Taxi("BREE");
//
//        // attaching taxi to location
//        Location loc1 = map.getLocation(2, 1);
//        loc1.addTaxi(taxi1);
//        Location loc2 = map.getLocation(3, 3);
//        loc2.addTaxi(taxi2);
//
//        scheduler.attach(taxi1);
//        scheduler.attach(taxi2);
//        Booking booking = new Booking(map, map.getLocation(1, 1), map.getLocation(9, 9));
//        scheduler.addBooking(booking, taxiType -> taxiType instanceof PartyBusTaxi);
//        assertFalse(taxi1.isFree());
//        assertTrue(taxi2.isFree());
//    }
//
//    @Test
//    void testFindNearestAvailableTaxi() {
//        Taxi closeTaxi = new Taxi("CLOSE");
//        Taxi farTaxi = new Taxi("FAR");
//        map.getLocation(1, 1).addTaxi(closeTaxi);
//        map.getLocation(4, 4).addTaxi(farTaxi);
//        TaxiBank.addtoBank(closeTaxi);
//        TaxiBank.addtoBank(farTaxi);
//        Booking booking = new Booking(map, map.getLocation(1, 1), map.getLocation(9, 9));
//        scheduler.addBooking(booking, taxiType -> taxiType instanceof PartyBusTaxi);
//        assertEquals(closeTaxi, booking.getTaxi());
//    }
}
