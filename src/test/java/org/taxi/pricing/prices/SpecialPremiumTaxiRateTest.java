package org.taxi.pricing.prices;

import org.junit.jupiter.api.Test;
import org.taxi.pricing.PriceCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialPremiumTaxiRateTest {

    @Test
    void testCalculatePriceBelowTariffRange() {
        PriceCalculator priceCalculator = new PriceCalculator();
        SpecialPremiumTaxiRate rate = new SpecialPremiumTaxiRate(priceCalculator);

        double price = rate.calculatePrice(0.3);

        assertEquals(4.8, price, "Price should be the initial charge for distances less than tariff range");
    }

    @Test
    void testCalculatePriceAboveTariffRange() {
        PriceCalculator priceCalculator = new PriceCalculator();
        SpecialPremiumTaxiRate rate = new SpecialPremiumTaxiRate(priceCalculator);

        double price = rate.calculatePrice(10.0);

        assertEquals(4.8 + (10.0 - 0.5) * 2.0, price, "Price should include initial charge and tariff for the distance above tariff range");
    }
}
