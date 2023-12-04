package org.taxi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;

class MainTest {
    static Main main;
    static Map map;
    @BeforeAll
    static void setup() {
        map = new Map(3,3);
        main = new Main(map);

        main.testAddToMap("RAWR", map.getLocation(2,2));
        main.testAddToMap("SKID", map.getLocation(2,2));
        main.testAddToMap("ZOOM", map.getLocation(1,0));
    }
    @Test
    void testRemoveTaxi() {
        main.testRemoveVehicle("RAWR");
        Taxi taxi = TaxiBank.returnSpecificTaxi("RAWR");
        Location taxiLocation = taxi.getLocation(map);

        assertEquals(null, taxiLocation);
    }

}