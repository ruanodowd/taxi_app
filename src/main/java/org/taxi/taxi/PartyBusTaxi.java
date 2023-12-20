package org.taxi.taxi;

import org.taxi.Main;
import org.taxi.pricing.prices.TaxiRate;

public class PartyBusTaxi extends Taxi{
    TaxiRate taxiRate;
    String taxiType = "Party Bus";
    public PartyBusTaxi(String registrationNumber) {
        super(registrationNumber);
        this.taxiRate = Main.priceCalculator.getPartyBusRate();
        speed = 1000;
    }

    @Override
    public TaxiRate getRate(){
        return taxiRate;
    }
}
