package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.taxi.booking.Booking;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;
import org.taxi.map.pathfinding.Dijkstra;

public class BookingTest {
    private Map map;
    private Booking booking;

    @BeforeEach
    public void Map() {
        map = new GridMap(10, 10);
        Location customerLocation = map.getLocation(0, 0);
        Location destination = map.getLocation(9, 9);

        booking = new Booking(map, customerLocation, destination);
    }

    @Test 
    public void otherTests() {
        assertEquals(map, booking.getMap());
        assertNotNull(booking.getPrice());
        assertEquals(new Dijkstra().calculateDistance(map, map.getLocation(0,0), map.getLocation(9,9)), booking.getDistance());
        assertEquals(map.getLocation(9, 9), booking.getDestination());
    }

}
