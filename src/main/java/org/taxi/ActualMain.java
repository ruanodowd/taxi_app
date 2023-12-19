package org.taxi;

import org.taxi.booking.Scheduler;
import org.taxi.map.GridMap;
import org.taxi.map.Map;
import org.taxi.pricing.PriceCalculator;
import org.taxi.userinterface.commandline.CommandLine;

public class ActualMain {
    public static PriceCalculator priceCalculator = new PriceCalculator();
    public static void main(String[] args) {
        CommandLine cli = CommandLine.getCommandLine();
        Map map = new GridMap(12,12);
        Scheduler scheduler = new Scheduler(map);
        cli.setScheduler(scheduler);
        cli.startScreen();
    }
}
