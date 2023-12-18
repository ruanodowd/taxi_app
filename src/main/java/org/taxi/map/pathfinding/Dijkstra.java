package org.taxi.map.pathfinding;

import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.datastructure.PriorityQueue;
import org.taxi.map.Location;
import org.taxi.map.GridMap;

import java.util.Comparator;

public class Dijkstra implements PathfindingMethod {
    static int weight = 1;

    public Dijkstra() {
    }

    @Override
    public double calculateDistance(GridMap map, Location start, Location end) {
        new Dijkstra().calculateRoute(map, start);
        return end.getDistance();
    }

    @Override
    public void calculateMinimumDistance(Location finish, Integer weight, Location start){
        Integer startDistance = start.getDistance();
        if (startDistance + weight < finish.getDistance()){
            finish.setDistance(startDistance + weight);
            DoublyLinkedList<Location> shortestPath = new DoublyLinkedList<>(start.getPathway());
            shortestPath.add(start);
            finish.setPathway(shortestPath);
        }
    }

    @Override
    public GridMap calculateRoute(GridMap map, Location start){
        start.setDistance(0);
        PriorityQueue<Location> unvisitedNodes = new PriorityQueue<>(Comparator.comparingInt(Location::getDistance));

        unvisitedNodes.add(start);

        while (!unvisitedNodes.isEmpty()) {
            Location locationPointer = unvisitedNodes.poll();
            for (Location adjacent: locationPointer.getNeighbouringLocations()){
                if (!adjacent.isVisited()){
                    calculateMinimumDistance(adjacent, weight, locationPointer);
                    unvisitedNodes.add(adjacent);
                }
            }
            locationPointer.setVisited(true);
        }
        return map;
    }
}