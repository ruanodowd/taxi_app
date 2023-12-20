package org.taxi.pricing.prices;

import org.taxi.pricing.PriceCalculator;

public class PremiumTaxiRate implements TaxiRate{
    //the initial charge is added at the start of a ride and covers the first 0.5 kilometers of a ride
    double initialCharge = 4.8;
    //tariff a is a price per kilometer for the first 15 kilometers of a ride
    double tariffA = 1.71;
    //tariff b is a price per kilometer that takes effect after 15 kilometers
    double tariffB = 2.0;
    double tariffARange = 0.5;
    double tariffBRange = 15;
    PriceCalculator priceCalculator;
    String taxiType = "Premium Taxi";
    public String getTaxiRateType() {
        return taxiType;
    }

    public PremiumTaxiRate(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public double calculatePrice(double distance) {
        double price;
        if (distance < tariffARange) {
            price = initialCharge;
        }

        else if (distance < tariffBRange) {
            price = initialCharge + (distance - tariffARange) * tariffA; 
        }

        else {
            price = initialCharge + ((tariffBRange - tariffARange) * tariffA) + ((distance - tariffBRange) * tariffB);  
        }

        return price;
    }
}
