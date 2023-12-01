package org.taxi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main implements VehicleHiringTest{
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Override
    public boolean testAddToMap(String reg, Location loc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testAddToMap'");
    }

    @Override
    public boolean testMoveVehicle(String reg, Location loc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testMoveVehicle'");
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

    public static void createMap(){
        ArrayList<Location> mapLocations = new ArrayList<>();
        Location Loc0a = new Location(0, 0);
        Location Loc1a = new Location(1, 0);
        Location Loc0b = new Location(0, 1);
        Location Loc1b = new Location(1, 1);
        Location[] list = {Loc1a, Loc0b};
        Loc0a.addNeighbourLocations(Arrays.stream(list).toList());
        list = new Location[]{Loc0a, Loc1b};
        Loc1a.addNeighbourLocations(Arrays.stream(list).toList());
        list = new Location[]{Loc0a, Loc1b};
        Loc0b.addNeighbourLocations(Arrays.stream(list).toList());
        list = new Location[]{Loc1b, Loc0a};
        Loc1b.addNeighbourLocations(Arrays.stream(list).toList());

        mapLocations.add(Loc0a);
        mapLocations.add(Loc1a);
        mapLocations.add(Loc0b);
        mapLocations.add(Loc1b);
        Map map = new Map(mapLocations);
        //for (int i = 0; i < mapLocations.size(); i++) {
        //    mapLocations.stream().filter(mapLocations.get(i).getX() == )
        //}
    }
}