package org.taxi.userinterface.commandline;

import org.taxi.ActualMain;
import org.taxi.booking.Booking;
import org.taxi.booking.Completion;
import org.taxi.booking.Scheduler;
import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;
import org.taxi.taxi.*;

import java.util.Scanner;
import java.util.function.Predicate;

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
        System.out.println("------------------------");
        scanner.nextLine();
        userLocationInputScreen();
    }
    public void userLocationInputScreen(){
        clearScreen();
        System.out.println("Enter your location in the form 'x, y'");
        Location from = processCoordinateString(scanner.nextLine());
        System.out.println("Enter your destination in the form 'x, y'");
        Location destination = processCoordinateString(scanner.nextLine());
        
        // asking what type of taxi you want 
        Predicate<Taxi> taxiType = null; 
        System.out.println("We have three types of taxis! Please select 1, 2 or 3. \nStandardTaxi (1) \nUrgentTaxi (2) \nPartyBus (3)");

        while (taxiType == null) {
            Integer type = scanner.nextInt(); 

            if (type == 1) {
                taxiType = taxi -> taxi instanceof NormalTaxi;
            } else if (type == 2) {
                taxiType = taxi -> taxi instanceof UrgentTaxi;
            } else if (type == 3) {
                taxiType = taxi -> taxi instanceof PartyBusTaxi;
            } else {
                System.out.println("Invalid selection. Please select 1, 2, or 3.");
                System.out.println("We have three types of taxis! Please select 1, 2 or 3. \nStandardTaxi (1) \nUrgentTaxi (2) \nPartyBus (3)");
            }
        }

        userBookingConfirmationScreen(from, destination, taxiType);
    }
    public Location processCoordinateString(String unprocessedCoordinates){
        String[] split = unprocessedCoordinates.split("\\D");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[split.length-1]);
        return scheduler.getMap().getLocation(x, y);
    }
    public void userBookingConfirmationScreen(Location location,Location destination, Predicate<Taxi> taxiType){
        Map map = scheduler.getMap();

        // need to add option and 
        Booking booking = new Booking(map, location, destination, ActualMain.priceCalculator.getStandardTaxiRate());

        scheduler.addBooking(booking, taxiType);
        System.out.println("The destination is " + booking.getDistance() +"km away");
        System.out.println("the taxi will cost " + booking.getPrice());
        System.out.println("Your taxis reg is " + booking.getTaxi().getRegistrationNumber());
        System.out.println("It is " + booking.getTaxi().getLocation(map).getDistance() + "km away");
        System.out.println("book taxi? (y/n)");
        String answer = scanner.nextLine();
        if (answer.contains("y")){
            showTaxiEnrouteDisplay(booking);
            showTravellingDisplay(booking);
            completeRide();
        } else {
            System.out.println("Route cancelled");
            scheduler.cancelBooking(booking);
        }

    }
    public void showTaxiEnrouteDisplay(Booking booking){
        System.out.println("showing enroute display");
        try {
            DoublyLinkedList<Location> route = booking.getTaxi()
                    .getLocation(scheduler.getMap())
                    .getPathway();
            route.add(booking.getTaxi().getLocation(booking.getMap()));
            route = route.reverse();
            showIterativeRouteMap(scheduler.getMap(), booking.getCustomerLocation(), route);
            System.out.println("first iter route map done");
        } catch (InterruptedException e) {
            System.out.println("ERROR ENROUTE");
            throw new RuntimeException(e);
        }

    }
    public void showTravellingDisplay(Booking booking){
        System.out.println("picked up driver");
        try {
            Thread.sleep(1000);
            DoublyLinkedList<Location> route = booking.getDestination().getPathway();
            route.add(booking.getDestination());
            showIterativeRouteMap(scheduler.getMap(), booking.getDestination(), route);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void completeRide(){
        System.out.println("Ride is completed");
        rateDriver();
    }
    public void rateDriver(){
        System.out.println("How many stars from 1-5 do you wish to rate your driver");
        try {
            Integer rating = scanner.nextInt();
            if (rating > 5 || rating < 0){
                throw new IllegalArgumentException("Womp Womp");
            }
            Taxi assignedTaxi = scheduler.getBookings().get(0).getTaxi();
            new Completion(assignedTaxi, rating);
            System.out.println("Thank you for reviewing");
            System.out.println("Just so you know the average rating for your taxi is: " + assignedTaxi.getRating());
            endProgramme();
            
        } catch (Exception e){
            System.out.println("Please enter a number between 1 and 5");
            rateDriver();
        }
    }

    public void endProgramme() {
        clearScreen();
        System.out.print("Do you want to make another booking? (y/n): ");
        String choice = scanner.nextLine();

        if (choice.equals("y")){
            clearScreen();
            userLocationInputScreen();
        } else if(choice.equals("n")){
            System.out.println("Thank you for using our app. Hope to see you soon!");
            System.exit(0);
        } else {
            endProgramme();
        }

    }
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
    public void showIterativeRouteMap(Map map, Location destination, DoublyLinkedList<Location> route) throws InterruptedException {
        int height = map.getHeight();
        int width = map.getWidth();
        for (Location currentLocation : route){
            currentLocation.setCovered(false);
        }
        for (Location currentLocation : route
             ) {
            Thread.sleep(1000);
            clearScreen();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Location location = map.getLocation(x, y);
                    if (currentLocation == map.getLocation(x, y)) {
                        System.out.print("\uD83D\uDE95 ");//taxi
                        currentLocation.setCovered(true);
                    }else if (destination == map.getLocation(x, y) && destination.getDistance() == 0) {
                            System.out.print("\uD83D\uDC68\uD83C\uDFFC\u200D\uD83E\uDDB0 ");//person
                        }
                    else if (destination == map.getLocation(x, y)) {
                        System.out.print("\uD83D\uDCCD ");//location
                    }
                     else if (route.contains(map.getLocation(x, y)) && !map.getLocation(x,y).isCovered()) {

                        System.out.print("\uD83D\uDFE9 ");
                    } else {
                        System.out.print("⬛ ");
                    }
                }
                System.out.print("\n");
            }
        }
    }
//    public void showRouteMap(Map map, Location destination, Location start){
//        int height = map.getHeight();
//        int width = map.getWidth();
//        DoublyLinkedList<Location> route = destination.getPathway();
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (destination == map.getLocation(x,y)){
//                    System.out.print("\uD83D\uDCCD ");
//                }
//                else if(start == map.getLocation(x, y)){
//                    System.out.print("\uD83D\uDE95 ");
//                }
//                else if (route.contains(map.getLocation(x, y))){
//                    System.out.print(CommandLineColours.GREEN +
//                            map.getLocation(x, y)
//                                    .getContainedTaxis()
//                                    .size() + "  "
//                            + CommandLineColours.RESET);
//                } else{
//                    System.out.print(map.getLocation(x, y)
//                            .getContainedTaxis()
//                            .size() + "  ");
//                }
//            }
//            System.out.print("\n");
//        }
//    }

}
