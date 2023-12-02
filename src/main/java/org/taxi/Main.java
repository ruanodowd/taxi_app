package org.taxi;

import java.util.List;

public class Main implements VehicleHiringTest{
    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Map(3,3);
    }

    @Override
    public boolean testAddToMap(String reg, Location loc) {
        loc.addTaxi(new Taxi(reg));
        return true;
    }

    @Override
    public boolean testMoveVehicle(String reg, Location loc) {
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

}