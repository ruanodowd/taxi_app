package org.taxi;

import org.junit.jupiter.api.Test;
import org.taxi.booking.Booking;
import org.taxi.booking.Scheduler;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;
import org.taxi.taxi.NormalTaxi;
import org.taxi.taxi.PartyBusTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.UrgentTaxi;
import org.taxi.userinterface.commandline.CommandLine;
import org.taxi.userinterface.commandline.Controller;
import org.taxi.userinterface.commandline.UserInterface;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CommandLineTest {
    private Controller controller;
    private GridMap map;
    
    @BeforeEach
    void setUp() {
        controller = Controller.getInstance();
        map = new GridMap(12,12); // Assuming this is a valid size for your map.
        controller.setMap(map);
        controller.setUp(); // This should initialise the system and add taxis to the map.
    }

    @Test
    void taxiShouldBeAtLocationAfterSetup() {
        Taxi taxi = new NormalTaxi("N0");
        map.getLocation(0,0).addTaxi(taxi);
        Location taxiLocation = taxi.getLocation(map);

        assertNotNull(taxiLocation, "Taxi should have a location after being added to the map.");
        assertEquals(0, taxiLocation.getX());
        assertEquals(0, taxiLocation.getY());
    }

    @Test
    void taxiShouldNotHaveLocationIfNotAdded() {
        Taxi taxi = new UrgentTaxi("U10"); // Taxi not added to any location
        Location taxiLocation = taxi.getLocation(map);

        assertNull(taxiLocation, "Taxi should not have a location if it hasn't been added to the map.");
    }

    @Test
    void testProcessCoordinateStringValidInput() {
        String input = "5,5";
        Location expectedLocation = map.getLocation(5,5);
        Location resultLocation = controller.processCoordinateString(input);

        assertEquals(expectedLocation, resultLocation, "The processCoordinateString method should return the correct location for a valid input string.");
    }

    @Test
    void testProcessCoordinateStringInvalidInput() {
        String input = "not a valid input";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            controller.processCoordinateString(input);
        }, "The processCoordinateString method should throw NumberFormatException for invalid input string.");
    }
}