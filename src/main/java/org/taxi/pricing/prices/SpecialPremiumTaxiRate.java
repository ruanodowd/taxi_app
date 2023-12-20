package org.taxi.pricing.prices;

import org.taxi.pricing.PriceCalculator;

public class    SpecialPremiumTaxiRate implements TaxiRate {
    //the initial charge is added at the start of a ride and covers the first 0.5 kilometers of a ride
    double initialCharge = 4.8;
    //tariff is the price per kilometer
    double tariff = 2.0;
    double tariffRange = 0.5;
    String taxiType = "Special Premium Taxi";
    public String getTaxiRateType() {
        return taxiType;
    }
    PriceCalculator priceCalculator;

    public SpecialPremiumTaxiRate(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public double calculatePrice(double distance) {
        double price;
        if (distance < tariffRange){
            price = initialCharge;
        }

        else {
            price = initialCharge + ((distance - tariffRange) * tariff);
        }
        
        return price;
    }
}
