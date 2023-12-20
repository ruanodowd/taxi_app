package org.taxi;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.taxi.booking.Booking;
import org.taxi.booking.Scheduler;
import org.taxi.map.GridMap;
import org.taxi.map.Map;
import org.taxi.map.pathfinding.Dijkstra;
import org.taxi.taxi.NormalTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.userinterface.commandline.CommandLine;
import org.taxi.userinterface.commandline.Controller;
import org.taxi.userinterface.commandline.UserInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SysOutCLITest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    Map map = new GridMap(2,2);
    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void out() {
        System.out.print("hello");
        assertEquals("hello", outContent.toString());
    }

    @Test
    public void err() {
        System.err.print("hello again");
        assertEquals("hello again", errContent.toString());
    }

    @org.junit.jupiter.api.Test
    public void testPromptLocationFromUser() {
        UserInterface.getCommandLine().promptLocationFromUser();
        assertTrue(outContent.toString().contains("Enter your location in the form 'x, y'"));
    }
    @org.junit.jupiter.api.Test
    public void testPromptDestinationFromUser() {
        UserInterface.getCommandLine().promptDestinationFromUser();
        assertTrue(outContent.toString().contains("Enter your destination in the form 'x, y'"));
    }
    @org.junit.jupiter.api.Test
    public void printRectangularMap() {
        UserInterface.getCommandLine().showRectangularMap(map, map.getHeight(), map.getWidth());
        assertTrue(outContent.toString().contains("0"));
    }
    @org.junit.jupiter.api.Test
    public void testPromptDriverRating() {
        UserInterface.getCommandLine().promptDriverRating();
        assertTrue(outContent.toString().contains("wish to rate your driver"));
        UserInterface.getCommandLine().promptDriverRatingAgain();
        assertTrue(outContent.toString().contains("Please enter"));
    }

    @org.junit.jupiter.api.Test
    public void testshowTravellingDisplay(){
        Scheduler scheduler = new Scheduler(map);
        Controller controller = Controller.getInstance();
        CommandLine cli = UserInterface.getCommandLine();
        cli.setScheduler(scheduler);
        controller.setMap(map);
        controller.setCli(cli);
        Taxi taxi1 = new NormalTaxi("N0");
        taxi1.setLocation(map, map.getLocation(0,0));
        scheduler.attach(taxi1);
        controller.setScheduler(scheduler);
        Booking booking = new Booking(map, map.getLocation(0,0), map.getLocation(1,1));
        scheduler.addBooking(booking, taxi -> taxi instanceof NormalTaxi);
        controller.showTravellingDisplay(booking);
        assertFalse(outContent.toString().contains("1"));
        controller.showTaxiEnrouteDisplay(booking);
        assertFalse(outContent.toString().contains("1"));
    }

    @org.junit.jupiter.api.Test
    public void testMap() {
        new Dijkstra().calculateRoute(map, map.getLocation(0,0));
        UserInterface.getCommandLine().printRouteMap(map, map.getLocation(0,0),map.getLocation(1,1).getPathway(), map.getLocation(1,0),2,2);
        assertFalse(outContent.toString().contains("1"));
    }
}
