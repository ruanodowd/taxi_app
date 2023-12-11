package org.taxi;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dijkstra {
    static int weight = 1;
    public static Map calculateRoute(Map map, Location start){
        start.setDistance(0);
        ArrayList<Location> visitedNodes = new ArrayList<>();
        ArrayList<Location> unvisitedNodes = new ArrayList<>();

        unvisitedNodes.add(start);

        while(unvisitedNodes.size() != 0){
            Location locationPointer = getNearestLocation(unvisitedNodes);
            unvisitedNodes.remove(locationPointer);
            for (Location adjacent: locationPointer.getNeighbouringLocations()){
                if (!visitedNodes.contains(adjacent)){
                    calculateMinimumDistance(adjacent, weight, locationPointer);
                    unvisitedNodes.add(adjacent);
                }
            }
            visitedNodes.add(locationPointer);
        }
        return map;
    }
    private static Location getNearestLocation(ArrayList<Location> unvisitedLocations){
        Location nearestLocation = null;
        int shortestDistance = Integer.MAX_VALUE;
        for (Location location: unvisitedLocations) {
            int locationDistance = weight;
            if (shortestDistance > locationDistance) {
                shortestDistance = locationDistance;
                nearestLocation = location;
            }
        }
        return nearestLocation;
    }

    // added this - U
    public static double calculateDistance(Map map, Location start, Location end) {
        calculateRoute(map, start); 
        return end.getDistance(); 
    }

    private static void calculateMinimumDistance(Location finish, Integer weight, Location start){
        Integer startDistance = start.getDistance();
        if (startDistance + weight < finish.getDistance()){
            finish.setDistance(startDistance + weight);
            LinkedList<Location> shortestPath = new LinkedList<>(start.getPathway());
            shortestPath.add(start);
            finish.setPathway(shortestPath);
        }
    }
}
