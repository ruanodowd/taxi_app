package org.taxi.map.pathfinding;

import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.datastructure.PriorityQueue;
import org.taxi.map.Location;
import org.taxi.map.Map;

import java.util.Comparator;

public class Dijkstra implements PathfindingMethod {
    static int weight = 1;

    public Dijkstra() {
    }

    @Override
    public double calculateDistance(Map map, Location start, Location end) {
        new Dijkstra().calculateRoute(map, start);
        return end.getDistance();
    }

    @Override
    public void calculateMinimumDistance(Location finish, Integer weight, Location start){
        // Retrieve the current shortest distance from the start node to the 'start' node.
        Integer startDistance = start.getDistance();
    
        // check if new path is shorter than current path
        if (startDistance + weight < finish.getDistance()){
            // If it's less, update 'finish' node's distance with the new shorter distance.
            finish.setDistance(startDistance + weight);
    
            // Create a new doubly linked list for 'finish' node's pathway by copying the pathway from 'start'.
            DoublyLinkedList<Location> shortestPath = new DoublyLinkedList<>(start.getPathway());
            
            // Add the 'start' node to the copied pathway.
            shortestPath.add(start);
    
            // Set this new pathway as the shortest path to the 'finish' node.
            finish.setPathway(shortestPath);
        }
    }

    @Override
    public Map calculateRoute(Map map, Location start){
        // Set the distance to the start location as 0 since it's the starting point
        start.setDistance(0);

        // Create a priority queue to store unvisited nodes ordered by shortest distance
        PriorityQueue<Location> unvisitedNodes = new PriorityQueue<>(Comparator.comparingInt(Location::getDistance));

        // Add the start location to the priority queue
        unvisitedNodes.add(start);

        while (!unvisitedNodes.isEmpty()) {
            // Remove and get the location with the shortest distance from the priority queue
            Location locationPointer = unvisitedNodes.poll();

            // Iterate through all neighbouring locations of the current location
            for (Location adjacent: locationPointer.getNeighbouringLocations()){
                // If this neighbouring location has not been visited yet
                if (!adjacent.isVisited()){
                    // Update the minimum distance to this neighbouring location
                    calculateMinimumDistance(adjacent, weight, locationPointer);

                    // Add the neighbouring location to the priority queue for further exploration
                    unvisitedNodes.add(adjacent);
                }
            }

            locationPointer.setVisited(true);
        }

        // Return the modified map with updated distances and pathways
        return map;
    }
}