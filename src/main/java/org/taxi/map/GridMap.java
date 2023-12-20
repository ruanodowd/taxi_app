package org.taxi.map;

import org.taxi.datastructure.ArrayList;

public class GridMap implements Map {
    private static ArrayList<Location> nodes;
    int width;
    int height;
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    GridMap(ArrayList<Location> nodes){
        GridMap.nodes = nodes;
    }

    // Overloads the 
    @Override
    public ArrayList<Location> getLocationNodes() {
        return nodes;
    }

    @Override
    public Location getLocation(int x, int y){
        try {
            return nodes
                .stream()
                .filter(n -> x == n.getX() && y == n.getY())
                .toList()
                .get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public GridMap(int height, int width){
        this.height = height;
        this.width = width;
        //this creates a map in a grid shape
        GridMap.nodes = new ArrayList<>();
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

    @Override
    public int countNodes(){
        return Math.toIntExact(nodes.size());
    }
}
