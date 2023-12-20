package org.taxi;

import org.junit.jupiter.api.Test;
import org.taxi.map.Location;
import org.taxi.datastructure.DoublyLinkedList;
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

    @Test
    public void testImprovedDistance() {
        Dijkstra dijkstra = new Dijkstra();
        Location start = new Location(3,3);
        start.setDistance(5);
        DoublyLinkedList<Location> startPathway = new DoublyLinkedList<>();
        startPathway.add(new Location(2,2));
        start.setPathway(startPathway);

        Location finish = new Location(3,3);
        finish.setDistance(10);
        DoublyLinkedList<Location> finishPathway = new DoublyLinkedList<>();
        finishPathway.add(new Location(4,4));
        finish.setPathway(finishPathway);

        dijkstra.calculateMinimumDistance(finish, 1, start);

        assertEquals(6, finish.getDistance());
        assertTrue(finish.getPathway().contains(start));
    }
}