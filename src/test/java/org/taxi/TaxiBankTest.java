package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.TaxiBank;

public class TaxiBankTest {
    @BeforeEach
    void setup() {
        TaxiBank.clear();
    }

    @Test
    void checkForOne() {
        Taxi t1 = new Taxi("14223123/WW/299");
        Taxi firstTaxi = TaxiBank.returnSpecificTaxi("14223123/WW/299");

        assertEquals(t1, firstTaxi);
    }

    @Test
    void checkForTwo() {
        Taxi t1 = new Taxi("1");
        Taxi t2 = new Taxi("2");

        Taxi firstTaxi = TaxiBank.returnSpecificTaxi("1");
        Taxi secondTaxi = TaxiBank.returnSpecificTaxi("2");

        assertEquals(t1, firstTaxi);
        assertEquals(t2, secondTaxi);

    }

    @Test
    void findTaxi(){
        Taxi t1 = new Taxi("1");
        Taxi t2 = new Taxi("2");
        Taxi t3 = new Taxi("3");


        assertEquals(t1, TaxiBank.returnSpecificTaxi("1"));
        assertEquals(t2, TaxiBank.returnSpecificTaxi("2"));
        assertEquals(t3, TaxiBank.returnSpecificTaxi("3"));
        assertNull(TaxiBank.returnSpecificTaxi("43"));
    }


}
