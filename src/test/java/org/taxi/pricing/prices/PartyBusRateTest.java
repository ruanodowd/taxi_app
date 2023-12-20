package org.taxi.pricing.prices;

import org.junit.jupiter.api.Test;
import org.taxi.pricing.PriceCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartyBusRateTest {

    @Test
    void testCalculatePrice() {
        PriceCalculator priceCalculator = new PriceCalculator();
        PartyBusRate rate = new PartyBusRate(priceCalculator);

        double price = rate.calculatePrice(10.0);

        assertEquals(200 + (10.0 * 5), price, "Price should include booking fee and tariff for the total distance");
    }
}
