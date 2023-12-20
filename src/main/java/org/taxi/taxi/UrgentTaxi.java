package org.taxi.taxi;

import org.taxi.ActualMain;
import org.taxi.pricing.PriceCalculator;
import org.taxi.pricing.prices.PartyBusRate;
import org.taxi.pricing.prices.TaxiRate;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
            return ActualMain.priceCalculator.getSpecialPremiumTaxiRate();
        }
        else if (time.getHour() > 20 || time.getHour() < 8){
            return ActualMain.priceCalculator.getSpecialPremiumTaxiRate();
        }
        else {
            return ActualMain.priceCalculator.getPremiumTaxiRate();
        }
    }
}