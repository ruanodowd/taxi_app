package org.taxi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.taxi.map.Location;
import org.taxi.map.GridMap;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.TaxiBank;

import java.util.List;

class MainTest {
    static Main main;
    static GridMap map;
    static int countTaxis;
    @BeforeEach
    void setup() {
        map = new GridMap(3,3);
        main = new Main(map);

        main.testAddToMap("RAWR", map.getLocation(2,2));
        main.testAddToMap("SKID", map.getLocation(2,2));
        main.testAddToMap("ZOOM", map.getLocation(1,0));
        countTaxis = 3;
    }

    @Test
    void testGetVehiclesInRange() {
        List<String> taxis =  main.testGetVehiclesInRange((map.getLocation(1, 1)), 3);

        assertEquals(countTaxis, taxis.size());
    }

    @Test
    void testRemoveVehicleFromLocation() {
        assertTrue(main.testRemoveVehicle("RAWR"), "Vehicle should be removed successfully");
        assertNull(TaxiBank.returnSpecificTaxi("RAWR").getLocation(map), "Vehicle should no longer have a location after removal");
        
        // Try to remove a vehicle that does not exist
        assertFalse(main.testRemoveVehicle("NON_EXISTENT_REG"), "Should return false for non-existent vehicle");
        
        // Try to remove a vehicle that has no location
        Taxi taxiWithoutLocation = new Taxi("NO_LOC");
        TaxiBank.addtoBank(taxiWithoutLocation); // Assuming there's a method to add taxis directly to the bank
        assertFalse(main.testRemoveVehicle("NO_LOC"), "Should return false if the vehicle has no location");
    }

    @Test
    void testMoveVehicle() {
        // Initial setup: place a taxi at location (2,2)
        String taxiReg = "RAWR";
        Location initialLocation = map.getLocation(2, 2);
        main.testAddToMap(taxiReg, initialLocation);
        
        // Move the taxi to a new location (1,1)
        Location newLocation = map.getLocation(1, 1);
        assertTrue(main.testMoveVehicle(taxiReg, newLocation), "Vehicle should move successfully");
        
        // Verify the taxi is at the new location
        Taxi movedTaxi = TaxiBank.returnSpecificTaxi(taxiReg);
        assertEquals(newLocation, movedTaxi.getLocation(map), "Vehicle should be at the new location after moving");
        
        // Verify the taxi is no longer at the old location
        assertFalse(initialLocation.getContainedTaxis().contains(movedTaxi), "Vehicle should no longer be at the old location");
        
        // Attempt to move a non-existent vehicle
        assertFalse(main.testMoveVehicle("NON_EXISTENT_REG", newLocation), "Should return false when attempting to move a non-existent vehicle");
        
        // Attempt to move a vehicle without a location
        new Taxi("NO_LOC");
        assertFalse(main.testMoveVehicle("NO_LOC", newLocation), "Should return false if the vehicle has no initial location");
    }
}