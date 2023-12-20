package org.taxi.taxi;

import org.taxi.Main;
import org.taxi.pricing.prices.TaxiRate;

public class NormalTaxi extends Taxi{
    TaxiRate taxiRate;

    // constructor for normal taxi
    public NormalTaxi(String registrationNumber) {
        super(registrationNumber);
        this.taxiRate = Main.priceCalculator.getPartyBusRate();
        speed = 800;
    }

}