package org.taxi;

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
        loc.addTaxi(new Taxi(reg));
    }
    @Override
    public boolean testAddToMap(String reg, Location loc) {
        //todo its more special than I thought
        return true;
    }

    @Override
    public boolean testMoveVehicle(String reg, Location loc) {
        //todo ill do it-ruan
        return true;
    }

    @Override
    public boolean testRemoveVehicle(String reg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testRemoveVehicle'");
    }

    @Override
    public Location testGetVehicleLoc(String reg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testGetVehicleLoc'");
    }

    @Override
    public List<String> testGetVehiclesInRange(Location loc, int r) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testGetVehiclesInRange'");
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