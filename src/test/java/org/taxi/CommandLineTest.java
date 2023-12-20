package org.taxi;

import org.junit.jupiter.api.Test;
import org.taxi.booking.Booking;
import org.taxi.booking.Scheduler;
import org.taxi.map.GridMap;
import org.taxi.map.Map;
import org.taxi.taxi.PartyBusTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.userinterface.commandline.CommandLine;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineTest {

    @Test
    void processCoordinateString() {
        Map map = new GridMap(2, 2);
        CommandLine cli = CommandLine.getCommandLine();
        Scheduler scheduler = new Scheduler(map);
        cli.setScheduler(scheduler);
        assertEquals(map.getLocation(2,1), cli.processCoordinateString("2, 1"));
    }
    @Test
    void testStartProgram() {
        CommandLine cli = CommandLine.getCommandLine();
        try {
            cli.showWelcomeScreen();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }


    }
    @Test
    void testGetCommandLine() {
        CommandLine cli = CommandLine.getCommandLine();
        assertEquals(cli, CommandLine.getCommandLine());
    }
    @Test
    void testGetScheduler() {
        Map map = new GridMap(2, 2);
        CommandLine cli = CommandLine.getCommandLine();
        Scheduler scheduler = new Scheduler(map);
        cli.setScheduler(scheduler);
        assertEquals(scheduler, cli.getScheduler());
    }
    @Test
    void showIterativeRouteMap() {
        Map map = new GridMap(2, 2);
        CommandLine cli = CommandLine.getCommandLine();
        Taxi taxi = new PartyBusTaxi("HeySalahAndJosh!");
        Scheduler scheduler = new Scheduler(map);
        cli.setScheduler(scheduler);
        //creating a route
        Booking booking = new Booking(map, map.getLocation(0,0), map.getLocation(1,1));
        scheduler.addBooking(booking, taxiType -> taxiType instanceof PartyBusTaxi);
        try {
            cli.showIterativeRouteMap(map , booking.getDestination(), map.getLocation(1,1).getPathway(), booking);
            assertTrue(true);
        } catch (InterruptedException e) {
            fail();
        }
    }
}