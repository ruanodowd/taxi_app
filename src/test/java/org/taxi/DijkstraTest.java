package org.taxi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void testCalculateRoute() {
        Map map = new Map(5,5);
        Location start = map.getLocation(0, 1);
        Location end = map.getLocation(3,2);
        Dijkstra.calculateRoute(map, start);
        assertEquals(4, end.getDistance());
    }
}