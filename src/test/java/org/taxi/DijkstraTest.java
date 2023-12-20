package org.taxi;

import org.junit.jupiter.api.Test;
import org.taxi.map.Location;
import org.taxi.map.GridMap;
import org.taxi.map.pathfinding.Dijkstra;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void testCalculateRoute() {
        GridMap map = new GridMap(5,5);
        Location start = map.getLocation(0, 1);
        Location end = map.getLocation(3,2);
        new Dijkstra().calculateRoute(map, start);
        assertEquals(4, end.getDistance());
    }
}