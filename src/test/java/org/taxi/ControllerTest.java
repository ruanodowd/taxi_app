package org.taxi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.taxi.booking.Booking;
import org.taxi.booking.Scheduler;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;
import org.taxi.taxi.NormalTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.userinterface.commandline.CommandLine;
import org.taxi.userinterface.commandline.Controller;

import java.util.function.Predicate;

import static org.mockito.Mockito.*;

public class ControllerTest {

    @Mock
    private CommandLine cli;

    @Mock
    private Map map = new GridMap(4,4);
    @Mock
    private Scheduler scheduler = new Scheduler(map);
    private Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = Controller.getInstance();
        controller.setCli(cli);
        controller.setScheduler(scheduler);
        controller.setMap(map);
    }

//    @Test
//    public void testSetUp() {
//        controller.setUp();
//        verify(map, times(6)).getLocation(anyInt(), anyInt());
//    }
//
//    @Test
//    public void testStartProgram() {
//        controller.startProgram();
//        verify(cli).showWelcomeScreen();
//    }
//
//    @Test
//    public void testUserBookingConfirmationScreen() {
//        Location location = mock(Location.class);
//        Predicate<Taxi> taxiType = taxi -> taxi instanceof NormalTaxi;
//        controller.userBookingConfirmationScreen(location, location, taxiType);
//        verify(cli).clearScreen();
//    }
//
//    @Test
//    public void testUserLocationInputScreen() {
//        when(cli.getStringFromUser()).thenReturn("1,1");
//        controller.userLocationInputScreen();
//        verify(cli, times(2)).getStringFromUser();
//    }

    @Test
    public void testEndProgramme() {
        when(cli.readYesOrNoAnswer()).thenReturn(false);
        controller.endProgramme();
        verify(cli).sayGoodbyeOnUserExit();
    }

    @Test
    public void testPromptForTaxiType() {
        when(cli.getIntegerFromUser()).thenReturn(1);
        controller.promptForTaxiType();
        verify(cli).showTaxiTypeChoice();
    }

//    @Test
//    public void testShowTaxiEnrouteDisplay() {
//        Booking booking = mock(Booking.class);
//        controller.showTaxiEnrouteDisplay(booking);
//        verify(cli).tellUserTaxiEnroute();
//    }
//
//    @Test
//    public void testShowTravellingDisplay() {
//        Booking booking = mock(Booking.class);
//        controller.showTravellingDisplay(booking);
//        verify(cli).sayTaxiHasArrived();
//    }
//
//    @Test
//    public void testRateDriver() {
//        when(cli.getIntegerFromUser()).thenReturn(5);
//        controller.rateDriver();
//        verify(cli).promptDriverRating();
//    }
//
//    @Test
//    public void testProcessCoordinateString() {
//        String coordinates = "1,1";
//        controller.processCoordinateString(coordinates);
//        verify(map).getLocation(anyInt(), anyInt());
//    }
}