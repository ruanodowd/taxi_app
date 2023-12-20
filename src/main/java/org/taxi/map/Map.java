package org.taxi.map;

import org.taxi.datastructure.ArrayList;

public interface Map {
    // store all the locations in a map
    ArrayList<Location> getLocationNodes();

    // get the location from the x and y cordinates
    Location getLocation(int x, int y);

    // count no of nodes
    int countNodes();

    // get height and width of map
    int getHeight();
    int getWidth();
}
