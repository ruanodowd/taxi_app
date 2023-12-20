package org.taxi.taxi;

import org.taxi.booking.Scheduler;
import org.taxi.datastructure.Hashmap;

import java.util.stream.Stream;

public class TaxiBank {
    // storage feature using Hashmap
    private static Hashmap<String, Taxi> allTaxis = new Hashmap<>();

    // be able to get all the taxis in terms of objects
    // Note: This method's signature changes as Hashmap doesn't support getting all values as a List
    public static Iterable<Hashmap.Entry<String, Taxi>> getAllTaxis() {
        return allTaxis;
    }

    public static Stream<Taxi> getAllTaxisStream() {
        return allTaxis.stream();
    }

    // be able to add to data structure
    public static void addtoBank(Taxi taxi) {
        allTaxis.put(taxi.getRegistrationNumber(), taxi);
    }

    // return the taxi from the re gistration number
    public static Taxi returnSpecificTaxi(String reg){
        return allTaxis.get(reg);
    }

    // clear the Hashmap
    public static void clear() {
        allTaxis = new Hashmap<>(); 
    }

    public void attachAll(Scheduler scheduler) {
        // Iterating over all entries in the hashmap
        for (Hashmap.Entry<String, Taxi> entry : allTaxis) {
            scheduler.attach(entry.getValue());
        }
    }
}
