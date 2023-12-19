package org.taxi.userinterface.commandline;

import org.taxi.ActualMain;
import org.taxi.booking.Booking;
import org.taxi.booking.Scheduler;
import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;

import java.util.Scanner;

public class CommandLine {
    public final static CommandLine commandLine = new CommandLine();

    private Scanner scanner;

    int DEFAULT_TAXI = 1;

    private Scheduler scheduler;
    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    private CommandLine(){
        this.scanner = new Scanner(System.in);
    }
    public void clearScreen() {
        System.out.print(CommandLineColours.FLUSH);
        System.out.flush();
    }

    public static CommandLine getCommandLine(){
        return commandLine;
    }
    public void startScreen() {
        clearScreen();
        System.out.println("Welcome to the Taxi App");
        System.out.println("Press any key to book");
        System.out.println("-----------------------");
        scanner.nextLine();
        userLocationInputScreen();
    }
    public void userLocationInputScreen(){
        clearScreen();
        System.out.println("Enter your location in the form 'x, y'");
        Location from = processCoordinateString(scanner.nextLine());
        System.out.println("Enter your destination in the form 'x, y'");
        Location destination = processCoordinateString(scanner.nextLine());
        userBookingConfirmationScreen(from, destination, DEFAULT_TAXI);
    }
    public void userBookingConfirmationScreen(Location location,Location destination, int taxiType){
        Map map = scheduler.getMap();
        Booking booking = new Booking(map, location, destination, ActualMain.priceCalculator.getStandardTaxiRate());
        System.out.println("The nearest taxi is {not implemented}km away");
        scheduler.addBooking(booking);
        System.out.println("the taxi will cost " + booking.getPrice());
        System.out.println("book taxi? (y/n)");
        String answer = scanner.nextLine();
        if (answer.contains("y")){
            showEnrouteDisplay(booking);
        } else {
            System.out.println("Route cancelled");
            scheduler.cancelBooking(booking);
        }

    }
    public Location processCoordinateString(String unprocessedCoordinates){
        String[] split = unprocessedCoordinates.split("\\D");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[split.length-1]);
        return scheduler.getMap().getLocation(x, y);
    }
    public void displayMap(){

    }
    public void bookTaxi(int[] location, int[] destination, int taxiType){

    }
    public void showEnrouteDisplay(Booking booking){
        System.out.println("showing entroute display");
        showRouteMap(scheduler.getMap(), booking.getDestination());
    }
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
    public void showRouteMap(Map map, Location destination){
        int height = map.getHeight();
        int width = map.getWidth();
        DoublyLinkedList<Location> route = destination.getPathway();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (route.contains(map.getLocation(x, y))){
                    System.out.print(CommandLineColours.GREEN +
                            map.getLocation(x, y)
                                    .getContainedTaxis()
                                    .size() + "  "
                            + CommandLineColours.RESET);
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
