package org.taxi;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private ArrayList<Location> nodes;
    Map(ArrayList<Location> nodes){
        this.nodes = nodes;
    }

    public ArrayList<Location> getLocationNodes() {
        return nodes;
    }

    public Location getLocation(int x,int y){
        return nodes
                .stream()
                .filter(n -> x == n.getX() && y == n.getY())
                .toList()
                .get(0);
    }

    public Map(){ //creates a basic 2x2 map which we know is correct(could be used for testing the better function)
        this.nodes = new ArrayList<>();
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
        list = new Location[]{Loc1a, Loc0b};
        Loc1b.linkNeighbourLocation(Arrays.stream(list).toList());

        for (Location location : Arrays.asList(Loc0a, Loc1a, Loc0b, Loc1b)) {
            nodes.add(location);
        }
    }

    public Map(int height, int width){
        //this creates a map in a grid shape
        this.nodes = new ArrayList<>();
        nodes.add(new Location(0,0));
        for (int x = 1; x < width; x++) {
            nodes.add(x, new Location(x,0));
            nodes.get(x).locationLink(nodes.get(x-1));
        }
        for (int y = 1; y < height; y++) {
            nodes.add(width*y, new Location(0,y));
            nodes.get(width*y).locationLink(nodes.get(width*(y-1)));
            for (int x = 1; x < width; x++) {
                int rowStartIndex = width*y;
                int indexNumber = rowStartIndex+x;
                nodes.add(indexNumber, new Location(x, y));
                nodes.get(indexNumber).locationLink(nodes.get((indexNumber-1)));
                nodes.get(indexNumber).locationLink(nodes.get((rowStartIndex-width)+x));
            }
        }
    }

    public int countNodes(){
        return Math.toIntExact(nodes.size());
    }


}
