package org.taxi.pricing;

import org.taxi.pricing.prices.*;

public class PriceCalculator {
    TaxiRate partyBusRate;
    TaxiRate premiumTaxiRate;
    TaxiRate specialPremiumTaxiRate;
    TaxiRate standardTaxiRate;
    TaxiRate taxiRate;

    public PriceCalculator() {
        partyBusRate = new PartyBusRate(this);
        premiumTaxiRate = new PremiumTaxiRate(this);
        specialPremiumTaxiRate = new SpecialPremiumTaxiRate(this);
        standardTaxiRate = new StandardTaxiRate(this);
    }
    public void setTaxiRateType(TaxiRate taxiRate){
        this.taxiRate = taxiRate;
    }
    public double calculatePrice(double distance){
        return taxiRate.calculatePrice(distance);
    }

    //getters for each state
    public TaxiRate getPartyBusRate() {
        return partyBusRate;
    }

    public TaxiRate getPremiumTaxiRate() {
        return premiumTaxiRate;
    }

    public TaxiRate getSpecialPremiumTaxiRate() {
        return specialPremiumTaxiRate;
    }

    public TaxiRate getStandardTaxiRate() {
        return standardTaxiRate;
    }

    public TaxiRate getTaxiRate() {
        return taxiRate;
    }
}
