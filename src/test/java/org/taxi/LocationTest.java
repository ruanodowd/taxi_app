package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.taxi.map.Location;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.TaxiBank;

public class LocationTest {

    static Location loc22 = new Location(1, 2);
    @BeforeAll
    static void setup() {
        loc22.addTaxi(new Taxi("RAWR"));
    }

    @Test
    void testRemoveTaxi() {
        Taxi taxi = TaxiBank.returnSpecificTaxi("RAWR");
        loc22.removeTaxi(taxi);
        
        assertEquals(false, loc22.getContainedTaxis().contains(taxi));
    }
}
