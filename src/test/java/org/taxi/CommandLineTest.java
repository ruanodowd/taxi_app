package org.taxi;

import org.junit.jupiter.api.Test;
import org.taxi.booking.Booking;
import org.taxi.booking.Scheduler;
import org.taxi.map.GridMap;
import org.taxi.map.Map;
import org.taxi.taxi.PartyBusTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.userinterface.commandline.CommandLine;
import org.taxi.userinterface.commandline.Controller;
import org.taxi.userinterface.commandline.UserInterface;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineTest {

    @Test
    void processCoordinateString() {
        Map map = new GridMap(2, 2);
        Controller.getInstance().setUp();
        assertEquals(map.getLocation(2,1), Controller.getInstance().processCoordinateString("2, 1"));
    }
//    @Test
//    void testStartProgram() {
//        CommandLine cli = CommandLine.getCommandLine();
//        try {
//            cli.showWelcomeScreen();
//            assertTrue(true);
//        } catch (Exception e) {
//            assertTrue(false);
//        }
//
//
//    
    @Test
    void testGetCommandLine() {
        CommandLine cli = UserInterface.getCommandLine();
        assertEquals(cli, UserInterface.getCommandLine());
    }
    @Test
    void showIterativeRouteMap() {
        Map map = new GridMap(2, 2);
        Controller ctlr = Controller.getInstance();
        ctlr.setMap(map);
        ctlr.setCli(UserInterface.getCommandLine());
        Taxi taxi = new PartyBusTaxi("HeySalahAndJosh!");
        map.getLocation(0,0).addTaxi(taxi);
        Scheduler scheduler = new Scheduler(map);
        scheduler.attach(taxi);
        ctlr.setScheduler(scheduler);
        Booking booking = new Booking(map, map.getLocation(0,0), map.getLocation(1,1));
        scheduler.addBooking(booking, taxiType -> taxiType instanceof PartyBusTaxi);
        try {
            assertTrue(true);
            ctlr.showIterativeRouteMap(map , booking.getDestination(), map.getLocation(1,1).getPathway(), booking);
        } catch (InterruptedException e) {
            fail();
        }
    }
}