package org.taxi;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private ArrayList<Location> nodes;
    Map(ArrayList<Location> nodes){
        this.nodes = nodes;
    }

    public static Map create2x2Map(){
        ArrayList<Location> mapLocations = new ArrayList<>();
        Location Loc0a = new Location(0, 0);
        Location Loc1a = new Location(1, 0);
        Location Loc0b = new Location(0, 1);
        Location Loc1b = new Location(1, 1);
        Location[] list = {Loc1a, Loc0b};
        Loc0a.linkNeighbourLocation(Arrays.stream(list).toList());
        list = new Location[]{Loc0a, Loc1b};
        Loc1a.linkNeighbourLocation(Arrays.stream(list).toList());
        list = new Location[]{Loc0a, Loc1b};
        Loc0b.linkNeighbourLocation(Arrays.stream(list).toList());
        list = new Location[]{Loc1b, Loc0a};
        Loc1b.linkNeighbourLocation(Arrays.stream(list).toList());

        mapLocations.add(Loc0a);
        mapLocations.add(Loc1a);
        mapLocations.add(Loc0b);
        mapLocations.add(Loc1b);
        return new Map(mapLocations);
        //for (int i = 0; i < mapLocations.size(); i++) {
        //    mapLocations.stream().filter(mapLocations.get(i).getX() == )
        //}
    }

    public Map(int height, int width){//this does what we wanted it to do, dont ask me to explain it i honestly dont havea  clue
        ArrayList<Location> mapLocations = new ArrayList<>();
        mapLocations.add(new Location());
        for (int i = 1; i < width; i++) {
            mapLocations.add(i, new Location());
            mapLocations.get(i).locationLink(mapLocations.get(i-1));
        }
        for (int j = 1; j < height; j++) {
            mapLocations.add(new Location());
            mapLocations.get(width*j).locationLink(mapLocations.get(width*(j-1)));
            for (int i = 1; i < width; i++) {
                mapLocations.add(i, new Location());
                mapLocations.get(i).locationLink(mapLocations.get((width*j)+i-1));
                mapLocations.get(i).locationLink(mapLocations.get((width*j-1)+i));
            }
        }
    }

    public int countNodes(){
        return Math.toIntExact(nodes.size());
    }


}
