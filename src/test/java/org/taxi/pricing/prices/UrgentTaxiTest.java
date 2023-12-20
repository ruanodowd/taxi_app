package org.taxi.pricing.prices;

import org.junit.jupiter.api.Test;
import org.taxi.Main;
import org.taxi.pricing.PriceCalculator;
import org.taxi.pricing.prices.TaxiRate;
import org.taxi.taxi.UrgentTaxi;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UrgentTaxiTest {

    @Test
    void testGetSpecialPremiumTaxiRateNewYears() {
        // Assume that we can control the current date/time
        LocalDateTime newYears = LocalDateTime.of(2023, 1, 1, 10, 0);
        UrgentTaxi taxi = new UrgentTaxi("URGENT1");
        
        // Mock the Main.priceCalculator to return the appropriate rate
        Main.priceCalculator = new MockPriceCalculator(newYears);
        
        TaxiRate rate = taxi.getRate();
        assertTrue(rate instanceof SpecialPremiumTaxiRate, "Should return SpecialPremiumTaxiRate on New Year's");
    }

    @Test
    void testGetSpecialPremiumTaxiRateNightTime() {
        // Assume that we can control the current date/time
        LocalDateTime nightTime = LocalDateTime.now().withHour(21);
        UrgentTaxi taxi = new UrgentTaxi("URGENT2");
        
        // Mock the Main.priceCalculator to return the appropriate rate
        Main.priceCalculator = new MockPriceCalculator(nightTime);
        
        TaxiRate rate = taxi.getRate();
        assertTrue(rate instanceof SpecialPremiumTaxiRate, "Should return SpecialPremiumTaxiRate during night time");
    }

    @Test
    void testGetPremiumTaxiRate() {
        // Assume that we can control the current date/time
        LocalDateTime dayTime = LocalDateTime.now().withHour(10);
        UrgentTaxi taxi = new UrgentTaxi("URGENT3");
        
        // Mock the Main.priceCalculator to return the appropriate rate
        Main.priceCalculator = new MockPriceCalculator(dayTime);
        
        TaxiRate rate = taxi.getRate();
        assertTrue(rate instanceof PremiumTaxiRate, "Should return PremiumTaxiRate during day time");
    }
    
    // A mock price calculator class would be needed to control the returned rate based on the time provided
    // This is a placeholder for the mock class
    class MockPriceCalculator extends PriceCalculator {
        LocalDateTime currentTime;
        
        MockPriceCalculator(LocalDateTime currentTime) {
            this.currentTime = currentTime;
        }
        
        // Override methods to return mock rates based on the current time
    }
}
