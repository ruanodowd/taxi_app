package org.taxi.pricing.prices;

import org.junit.jupiter.api.Test;
import org.taxi.pricing.PriceCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PremiumTaxiRateTest {

    @Test
    void testCalculatePriceLessThanTariffARange() {
        PriceCalculator priceCalculator = new PriceCalculator();
        PremiumTaxiRate premiumTaxiRate = new PremiumTaxiRate(priceCalculator);

        double price = premiumTaxiRate.calculatePrice(0.3);

        assertEquals(4.8, price, "Price should be the initial charge for distances less than Tariff A range");
    }

    @Test
    void testCalculatePriceLessThanTariffBRange() {
        PriceCalculator priceCalculator = new PriceCalculator();
        PremiumTaxiRate premiumTaxiRate = new PremiumTaxiRate(priceCalculator);

        double price = premiumTaxiRate.calculatePrice(10.0);

        assertEquals(4.8 + (10.0 - 0.5) * 1.71, price, "Price should account for Tariff A rate for distances less than Tariff B range");
    }

    @Test
    void testCalculatePriceMoreThanTariffBRange() {
        PriceCalculator priceCalculator = new PriceCalculator();
        PremiumTaxiRate premiumTaxiRate = new PremiumTaxiRate(priceCalculator);

        double price = premiumTaxiRate.calculatePrice(20.0);

        assertEquals(4.8 + ((15 - 0.5) * 1.71) + ((20 - 15) * 2.0), price, "Price should account for Tariff A rate up to 15 km and Tariff B rate thereafter");
    }
}
