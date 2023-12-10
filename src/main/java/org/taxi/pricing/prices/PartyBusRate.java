package org.taxi.pricing.prices;

import org.taxi.pricing.PriceCalculator;

public class PartyBusRate implements TaxiRate {
    double bookingFee = 200; //base price for booking the party bus
    double tariff = 5; //additional price per kilometer
    PriceCalculator priceCalculator;

    public PartyBusRate(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public double calculatePrice(int distance) {
        return bookingFee * (distance*tariff);
    }
}
