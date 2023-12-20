package org.taxi.pricing.prices;

import org.junit.jupiter.api.Test;
import org.taxi.pricing.PriceCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardTaxiRateTest {
    @Test
    void testCalculatePriceLessThanTariffARange() {
        PriceCalculator priceCalculator = new PriceCalculator();
        StandardTaxiRate standardTaxiRate = new StandardTaxiRate(priceCalculator);

        double price = standardTaxiRate.calculatePrice(0.3);

        assertEquals(4.2, price);
    }

    @Test
    void testCalculatePriceLessThanTariffBRange() {
        PriceCalculator priceCalculator = new PriceCalculator();
        StandardTaxiRate standardTaxiRate = new StandardTaxiRate(priceCalculator);

        double price = standardTaxiRate.calculatePrice(10.0);

        assertEquals(4.2 + (10.0 - 0.5) * 1.3, price);
    }

    @Test
    void testCalculatePriceMoreThanTariffBRange() {
        PriceCalculator priceCalculator = new PriceCalculator();
        StandardTaxiRate standardTaxiRate = new StandardTaxiRate(priceCalculator);

        double price = standardTaxiRate.calculatePrice(20.0);

        assertEquals(4.2 + ((15 - 0.5) * 1.3) + ((20 - 15) * 1.65), price);
    }
}