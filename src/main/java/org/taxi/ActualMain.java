package org.taxi;

import org.taxi.booking.Scheduler;
import org.taxi.map.GridMap;
import org.taxi.map.Map;
import org.taxi.pricing.PriceCalculator;
import org.taxi.taxi.NormalTaxi;
import org.taxi.taxi.PartyBusTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.UrgentTaxi;
import org.taxi.userinterface.commandline.CommandLine;

public class ActualMain {
    public static PriceCalculator priceCalculator = new PriceCalculator();
    public static void main(String[] args) {
        CommandLine cli = CommandLine.getCommandLine();
        Map map = new GridMap(12,12);
        Taxi taxi = new PartyBusTaxi("RAWR - P6");
        map.getLocation(6,6).addTaxi(taxi);
        Taxi partyBus = new PartyBusTaxi("P0");
        map.getLocation(0,0).addTaxi(partyBus);
        Taxi taxi2 = new NormalTaxi("N6");
        map.getLocation(6,6).addTaxi(taxi2);
        Taxi NormalTaxi = new NormalTaxi("N0");
        map.getLocation(0,0).addTaxi(NormalTaxi);
        Taxi taxi3 = new UrgentTaxi("U6");
        map.getLocation(6,6).addTaxi(taxi3);
        Taxi UrgentTaxi = new UrgentTaxi("U0");
        map.getLocation(0,0).addTaxi(UrgentTaxi);
        Scheduler scheduler = new Scheduler(map);
        cli.setScheduler(scheduler);
        cli.startScreen();
    }
}
