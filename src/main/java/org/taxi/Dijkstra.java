package org.taxi;

import java.util.Comparator;

public class Dijkstra {
    static int weight = 1;
     public static Map calculateRoute(Map map, Location start){
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

    public static double calculateDistance(Map map, Location start, Location end) {
        calculateRoute(map, start); 
        return end.getDistance(); 
    }

    private static void calculateMinimumDistance(Location finish, Integer weight, Location start){
        Integer startDistance = start.getDistance();
        if (startDistance + weight < finish.getDistance()){
            finish.setDistance(startDistance + weight);
            DoublyLinkedList<Location> shortestPath = new DoublyLinkedList<>(start.getPathway());
            shortestPath.add(start);
            finish.setPathway(shortestPath);
        }
    }
}