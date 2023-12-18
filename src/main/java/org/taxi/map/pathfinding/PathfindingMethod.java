package org.taxi.map.pathfinding;

import org.taxi.map.GridMap;
import org.taxi.map.Location;

public interface PathfindingMethod {
    double calculateDistance(GridMap map, Location start, Location end);

    void calculateMinimumDistance(Location finish, Integer weight, Location start);

    GridMap calculateRoute(GridMap map, Location start);
}
