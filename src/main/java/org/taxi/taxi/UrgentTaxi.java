package org.taxi.taxi;

import org.taxi.Main;
import org.taxi.pricing.prices.TaxiRate;

import java.time.LocalDateTime;

public class UrgentTaxi extends Taxi {
    TaxiRate taxiRate;
    public UrgentTaxi(String registrationNumber) {
        super(registrationNumber);
        speed = 400;
    }
    @Override
    public TaxiRate getRate() {
        LocalDateTime time = LocalDateTime.now();
        if (time.getDayOfYear() == 360 || time.getDayOfYear() == 1){
            return Main.priceCalculator.getSpecialPremiumTaxiRate();
        }
        else if (time.getHour() > 20 || time.getHour() < 8){
            return Main.priceCalculator.getSpecialPremiumTaxiRate();
        }
        else {
            return Main.priceCalculator.getPremiumTaxiRate();
        }
    }
}