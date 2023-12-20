package org.taxi;
import org.junit.jupiter.api.Test;
import org.taxi.map.Location;
import org.taxi.userinterface.commandline.Controller;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
public class ControlerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        // Instantiate a real Controller, which will also create instances of its dependencies.
        controller = Controller.getInstance();
        controller.setUp();
    }

    @Test
    void testSingletonInstance() {
        // Verify that the Controller is a singleton.
        Controller anotherInstance = Controller.getInstance();
        assertSame(controller, anotherInstance, "There should only be one instance of Controller.");
    }

    @Test
    void testSetUp() {
        // Verify that the setup method initializes the map and scheduler.
        assertNotNull(controller.getMap(), "Map should be initialized after setup.");
        assertNotNull(controller.getScheduler(), "Scheduler should be initialized after setup.");
    }

    @Test
    void testProcessCoordinateString() {
        // This will test the conversion of a string to a Location object.
        String inputCoordinates = "5,5";
        Location location = controller.processCoordinateString(inputCoordinates);
        assertNotNull(location, "Location should not be null.");
        assertEquals(5, location.getX(), "X coordinate should match input.");
        assertEquals(5, location.getY(), "Y coordinate should match input.");
    }
}