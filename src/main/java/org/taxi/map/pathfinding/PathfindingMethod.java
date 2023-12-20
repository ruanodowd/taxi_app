package org.taxi.map.pathfinding;

import org.taxi.map.Location;
import org.taxi.map.Map;

public interface PathfindingMethod {
    double calculateDistance(Map map, Location start, Location end);

    void calculateMinimumDistance(Location finish, Integer weight, Location start);

    Map calculateRoute(Map map, Location start);
}
