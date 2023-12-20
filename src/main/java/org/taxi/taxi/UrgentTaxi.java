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
    }

}