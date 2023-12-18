package org.taxi.userinterface.commandline;

import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;

import java.util.Scanner;

public class CommandLine {
    public final static CommandLine commandLine = new CommandLine();
    private CommandLine(){
        Scanner scanner = new Scanner(System.in);
    }
    private Scanner scanner;
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static CommandLine getCommandLine(){
        return commandLine;
    }
    public void startScreen() {
        System.out.println("Welcome to the Taxi App");
        System.out.println("Please select an option:");
        System.out.println("1. Book a taxi");
        System.out.println("2. View all taxis");
        System.out.println("3. Exit");
    }
    public void displayMap(){

    }
    public void bookTaxi(){}
    public void showEnrouteDisplay(){}
    public void completeRide(){}
    public void showRectangularMap(GridMap map, int height, int width){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map.getLocation(x, y)
                        .getContainedTaxis()
                        .size() + " ");
            }
            System.out.print("\n");
        }
    }
    public void showRouteMap(Map map, int height, int width, Location destination){
        DoublyLinkedList<Location> route = destination.getPathway();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (route.contains(map.getLocation(x, y))){
                    System.out.print("\033[32m" +
                            map.getLocation(x, y)
                                    .getContainedTaxis()
                                    .size() + "  \033[0m");
                } else{
                    System.out.print(map.getLocation(x, y)
                            .getContainedTaxis()
                            .size() + "  ");
                }
            }
            System.out.print("\n");
        }
    }
}
