package org.taxi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaxiBankTest {
    @BeforeEach
    void setup() {
        TaxiBank.clear();
    }

    @Test
    void checkForOne() {
        Taxi t1 = new Taxi("22/34/54");
        Taxi firstTaxi = TaxiBank.returnSpecificTaxi("22/34/54");

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
        assertEquals(null, TaxiBank.returnSpecificTaxi("43"));
    }


}
