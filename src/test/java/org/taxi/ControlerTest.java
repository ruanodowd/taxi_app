package org.taxi;

import org.taxi.userinterface.commandline.Controller;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
public class ControlerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        // Since we're not mocking, we need to ensure that we have all the real dependencies set up.
        controller = Controller.getInstance();
        controller.setUp(); // Assuming setUp() method initializes everything needed for the tests.
    }

    @Test
    public void testSingletonInstance() {
        Controller anotherInstance = Controller.getInstance();
        assertSame(controller, anotherInstance);
        assertSame(controller, anotherInstance);
    }

    @Test
    public void testSetUp() {
        // Assuming setUp method initializes a map with a size of 12x12
        assertNotNull("Map should not be null after setup", controller.getMap().getLocationNodes());
        assertEquals("Map should be 12x12", 12, controller.getMap().getWidth());
        assertEquals("Map should be 12x12", 12, controller.getMap().getHeight());
    }

    @Test
    public void testProcessCoordinateString() {
        // This will test the conversion of a string to a Location object.
        String inputCoordinates = "5,5";
        Location location = controller.processCoordinateString(inputCoordinates);
        assertNotNull("Location should not be null", location);
        assertEquals("X coordinate should be 5", 5, location.getX());
        assertEquals("Y coordinate should be 5", 5, location.getY());
    }
}
