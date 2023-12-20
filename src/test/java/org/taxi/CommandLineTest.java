package org.taxi;

import org.junit.jupiter.api.Test;
import org.taxi.booking.Scheduler;
import org.taxi.map.GridMap;
import org.taxi.map.Map;
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
}