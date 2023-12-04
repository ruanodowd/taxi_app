package org.taxi;

import java.util.ArrayList;
import java.util.List;

public class Main implements VehicleHiringTest{
    public Map map;
    public Main(Map map) {
        this.map = map;
    }

    public static void main(String[] args) {
        Map map = new Map(3, 3);
        Main main = new Main(map);

        main.testAddToMap("RAWR", map.getLocation(2,2));
        main.testAddToMap("SKID", map.getLocation(2,2));
        main.testAddToMap("ZOOM", map.getLocation(1,0));
        showRectangularMap(map, 3, 3);
        
        main.testRemoveVehicle("RAWR");
        showRectangularMap(map, 3, 3);
        
        System.out.println(TaxiBank.returnSpecificTaxi("RAWR"));
    }

    public static void addTaxiToMap(String reg, Location loc) {
        // added the location parameter
        loc.addTaxi(new Taxi(reg));
    }
    @Override
    public boolean testAddToMap(String reg, Location loc) {
        //todo its more special than I thought
        // the taxi bank will help with this - ushen
        loc.addTaxi(new Taxi(reg));
        return true;
    }

    @Override
    public boolean testMoveVehicle(String reg, Location loc) {
        //todo ill do it-ruan
        return true;
    }

    @Override
    public boolean testRemoveVehicle(String reg) {
        // return the taxi associated with the registration
        Taxi taxi = TaxiBank.returnSpecificTaxi(reg);
        
        
        // check if null 
        if (taxi.equals(null)) {
            return false;
        }

        // removes taxi from the location where it is. 
        testGetVehicleLoc(reg).removeTaxi(taxi);
        
        return true;
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
        List<String> taxis = new ArrayList<>();

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
                Location location = map.getLocation(i,j);
                if (!(location == null)) {
                    for (Taxi taxi: location.getContainedTaxis()) {
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