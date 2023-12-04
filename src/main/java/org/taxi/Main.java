package org.taxi;

import java.util.ArrayList;
import java.util.List;

public class Main implements VehicleHiringTest{
    public static void main(String[] args) {
        Map map = new Map(3,3);
        addTaxiToMap("RAWR", map.getLocation(2,2));
        addTaxiToMap("SKID", map.getLocation(2,2));
        addTaxiToMap("ZOOM", map.getLocation(1,0));
        showRectangularMap(map,3, 3);
    }

    public static void addTaxiToMap(String reg, Location loc) {
        // added the location parameter
        loc.addTaxi(new Taxi(reg));
    }
    @Override
    public boolean testAddToMap(String reg, Location loc) {
        //todo its more special than I thought
        // the taxi bank will help with this - ushen
        return true;
    }

    @Override
    public boolean testMoveVehicle(String reg, Location loc) {
        //todo ill do it-ruan
        return true;
    }

    @Override
    public boolean testRemoveVehicle(String reg) {
        // what I want to do. Remove vehicle completely from the map. 
        // there should only be one taxi with the registration in all the locations. 
        // I can go into the taxi class - how to get all the taxi that exist? 
        Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
        
        
        // check if null 
        if (taxi.equals(null)) {
            return false;
        }

        // removes taxi from the location where it is. 
        testGetVehicleLoc(reg).getContainedTaxis().remove(taxi);
        
        // not sure if I need this code, maybe line above is enough.
        //taxi.setPosition(null);

        return true;
    }
    
    @Override
    public Location testGetVehicleLoc(String reg) {
        Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
        if (taxi.equals(null)) {
            return null;
        }
        
        // issue here. We should have a global map variable that we can pass in
        // I'm putting in new map so that the code doesn't blow up.
        return taxi.getLocation(new Map());
    }

    @Override
    public List<String> testGetVehiclesInRange(Location loc, int r) {
        // getting a list of registrations (ideally taxi objects however, return type suggests it's a string)
        List<String> taxis = new ArrayList<>();

        // when we have the location we need to get all the taxi's from that location and all the neighbouring locations
        for (Taxi taxi: loc.getContainedTaxis()) {
            taxis.add(taxi.getRegistrationNumber());
        }

        // figuring out the neighbouring locations, I wouldn't worry about calling locations that aren't in the bounds for now.
        // because I've edited the getLocation class to return null. I won't add those to the array. 
        int upperXBound = loc.getX() + 2*r;
        int lowerXBound = loc.getX() - 2*r;

        int upperYBound = loc.getY() + 2*r;
        int lowerYBound = loc.getY() - 2*r;

        for (int i = lowerXBound; i <= upperXBound; i++) {
            for (int j = lowerYBound; j<= upperYBound; j++) {
                // there's an issue here.I can't call getLocation directly. I need an instance of Map. 
                // we need a global map. 
                for (Taxi taxi: new Map().getLocation(i,j).getContainedTaxis()) {
                    if (taxi.equals(null)) {
                        return null;
                    }
                    taxis.add(taxi.getRegistrationNumber());
                }
            }
        }
        return taxis;
    }

    public static void showRectangularMap(Map map, int height, int width){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map.getLocation(x, y)
                        .getContainedTaxis()
                        .size() + " ");
            }
            System.out.print("\n");
        }
    }
}