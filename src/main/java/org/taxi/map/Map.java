package org.taxi.map;

import org.taxi.datastructure.ArrayList;

public interface Map {
    ArrayList<Location> getLocationNodes();

    Location getLocation(int x, int y);

    int countNodes();

    int getHeight();

    int getWidth();
}
