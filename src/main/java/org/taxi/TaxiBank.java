package org.taxi;
import java.util.List;
import java.util.ArrayList;

public class TaxiBank {
    private static List<Taxi> allTaxis = new ArrayList<>();

    public static List<Taxi> getAllTaxis() {
        return allTaxis;
    }

    public static void addtoBank(Taxi taxi) {
        allTaxis.add(taxi);
    }

    public static Taxi returnSpecificTaxi(String reg){
        for (Taxi taxi: allTaxis) {
            if (reg.equals(taxi.getRegistrationNumber())) {
                return taxi;
            }
        }
        return null;
    }

    public static void clear() {
        allTaxis = new ArrayList<>(); 
    }
    


}
