package org.taxi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taxi.map.Location;
import org.taxi.map.GridMap;
import org.taxi.taxi.Taxi;

public class TaxiTest {
    private Taxi taxi;
    private GridMap map;

    @BeforeEach
    void setup() {
        map = new GridMap(5, 5);
        taxi = new Taxi("RAWR");
    }

    @Test
    void testTaxiInitialization() {
        assertEquals("RAWR", taxi.getRegistrationNumber());
        assertTrue(taxi.isFree());
    }

    @Test
    void testGetLocation() {
        Location loc = map.getLocation(1, 1);
        loc.addTaxi(taxi);
        assertEquals(loc, taxi.getLocation(map));
    }

    @Test
    void testUpdateTaxiLocation() {
        Location newLocation = map.getLocation(2, 2);
        taxi.setLocation(map, newLocation);
        assertEquals(newLocation, taxi.getLocation(map));
    }

    @Test
    void testSetFree() {
        taxi.setFree(false);
        assertFalse(taxi.isFree());
    }
}
