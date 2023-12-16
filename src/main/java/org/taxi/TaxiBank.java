package org.taxi;
import java.util.List;
import java.util.ArrayList;

public class TaxiBank {
    // storage feature
    private static List<Taxi> allTaxis = new ArrayList<>();

    // be able to get all the taxis in terms of objects
    public static List<Taxi> getAllTaxis() {
        return allTaxis;
    }

    // be able to add to data structure
    public static void addtoBank(Taxi taxi) {
        allTaxis.add(taxi);
    }

    // return the taxi from the e
    public static Taxi returnSpecificTaxi(String reg){
        for (Taxi taxi: allTaxis) {
            if (reg.equals(taxi.getRegistrationNumber())) {
                return taxi;
            }
        }
        return null;
    }

    // 
    public static void clear() {
        allTaxis = new ArrayList<>(); 
    }

    public void attachAll(Scheduler scheduler) {
        for (Taxi taxi: allTaxis) {
            scheduler.attach(taxi);
        }
    }
}
