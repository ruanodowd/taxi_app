package org.taxi;

import org.taxi.datastructure.ArrayList;
import org.taxi.map.Location;
import org.taxi.map.GridMap;

import org.taxi.pricing.PriceCalculator;
import org.taxi.taxi.*;

import java.util.List;

import org.taxi.userinterface.commandline.Controller;

public class Main implements VehicleHiringTest {
    public GridMap map;
    public static PriceCalculator priceCalculator = new PriceCalculator();

    public Main(GridMap map) {
        this.map = map;
    }

    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        controller.setUp();
        controller.startProgram();
//        cli.showWelcomeScreen();
    }

    @Override
    public boolean testAddToMap(String reg, Location loc) {
        loc.addTaxi(new Taxi(reg));
        return true;
    }

    @Override
    public boolean testMoveVehicle(String reg, Location loc) {
        // what I need to do. When put the reg and location.
        // take the taxi from current location move it to the final location

        // find taxi
        Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
        if (taxi == null) {
            return false;
        }


        if (taxi.getLocation(map) == null) {
            return false;
        }

        // remove from location
        testRemoveVehicle(reg);

        // add the taxi to the location
        loc.addTaxi(taxi);

        return true;   
    }

    @Override
    public boolean testRemoveVehicle(String reg) {
        try {
            // return the taxi associated with the registration
            Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
    
    
            // check if null
            if (taxi.equals(null)) {
                return false;
            }
    
            // removes taxi from the location where it is.
            Location loc = testGetVehicleLoc(reg);
            if (loc == null) {
                return false;
            }
    
    
            loc.removeTaxi(taxi);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    } 

    @Override
    public Location testGetVehicleLoc(String reg) {
        Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
        if (taxi.equals(null)) {
            return null;
        }

        return taxi.getLocation(map);
    }

    @Override
    public List<String> testGetVehiclesInRange(Location loc, int r) {
        // getting a list of registrations (ideally taxi objects however, return type suggests it's a string)
        ArrayList<String> taxis = new ArrayList<>();

        for (Taxi taxi : loc.getContainedTaxis()) {
            taxis.add(taxi.getRegistrationNumber());
        }

        // figuring out the neighbouring locations, I wouldn't worry about calling locations that aren't in the bounds for now.
        // because I've edited the getLocation class to return null. I won't add those to the array.
        int upperXBound = loc.getX() + 2 * r;
        int lowerXBound = loc.getX() - 2 * r;

        int upperYBound = loc.getY() + 2 * r;
        int lowerYBound = loc.getY() - 2 * r;

        for (int i = lowerXBound; i <= upperXBound; i++) {
            for (int j = lowerYBound; j <= upperYBound; j++) {
                Location location = map.getLocation(i, j);
                if (!(location == null)) {
                    for (Taxi taxi : location.getContainedTaxis()) {
                        String reg = taxi.getRegistrationNumber();
                        if (!reg.equals(null)) {
                            taxis.add(reg);
                        }
                    }
                }
            }
        }
        return taxis;
    }
}