package org.taxi.booking;

import org.taxi.taxi.Taxi;
import org.taxi.map.Location;

public class Booking {
    private Taxi allocatedTaxi;
    private Location location;


    public Booking (Location location) {
        this.location = location;
    }



    public Location getCustomerLocation() {
        return location;
    }

    public void setTaxi(Taxi taxi) {
        allocatedTaxi = taxi;
    }

    public Taxi getTaxi() {
        return allocatedTaxi;
    }

}
