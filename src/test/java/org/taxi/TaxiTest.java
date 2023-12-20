package org.taxi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taxi.map.Location;
import org.taxi.map.GridMap;
import org.taxi.taxi.Taxi;
import org.taxi.pricing.prices.PremiumTaxiRate;


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
    void testsetTaxiFree() {
        taxi.setFree(false);
        assertFalse(taxi.isFree());
    }
    @Test
    void testIsFree() {
        Taxi taxi = new Taxi("1234");
        assertTrue(taxi.isFree());
    }

    @Test
    void testSetFree() {
        Taxi taxi = new Taxi("1234");
        taxi.setFree(false);
        assertFalse(taxi.isFree());
    }

    @Test
    void testGetRegistrationNumber() {
        Taxi taxi = new Taxi("1234");
        assertEquals("1234", taxi.getRegistrationNumber());
    }

    @Test
    void testSetRegistrationNumber() {
        Taxi taxi = new Taxi("1234");
        taxi.setRegistrationNumber("5678");
        assertEquals("5678", taxi.getRegistrationNumber());
    }


    @Test
    void testSetLocation() {
        Taxi taxi = new Taxi("1234");
        GridMap map = new GridMap(10, 10);
        Location location = new Location(5, 5);
        taxi.setLocation(map, location);
        assertTrue(location.getContainedTaxis().contains(taxi));
    }

    @Test
    void testGetRateStandard() {
        Taxi taxi = new Taxi("1234");
        assertInstanceOf(PremiumTaxiRate.class, taxi.getRate());
    }

}

