package org.taxi.taxi;

import org.taxi.ActualMain;
import org.taxi.pricing.PriceCalculator;
import org.taxi.pricing.prices.PartyBusRate;
import org.taxi.pricing.prices.TaxiRate;

public class PartyBusTaxi extends Taxi{
    TaxiRate taxiRate;
    public PartyBusTaxi(String registrationNumber) {
        super(registrationNumber);
        this.taxiRate = ActualMain.priceCalculator.getPartyBusRate();
    }

    @Override
    public TaxiRate getRate(){
        return taxiRate;
    }
}
