package org.taxi.userinterface.commandline;


import org.taxi.booking.Scheduler;
import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;

import java.util.Scanner;

public class CommandLine implements UserInterface {
    public final static CommandLine commandLine = new CommandLine();

    private final Scanner scanner;

    private Scheduler scheduler;
    
    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    private CommandLine() {
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void clearScreen() {
        System.out.print(CommandLineColours.FLUSH);
        System.out.flush();
    }

    @Override
    public void showWelcomeScreen() {
        clearScreen();
        System.out.println("Welcome to the Taxi App");
        System.out.println("Press any key to book");
        System.out.println("------------------------");
        scanner.nextLine();
    }


    @Override
    public void promptDestinationFromUser() {
        System.out.println("Enter your destination in the form 'x, y'");
    }

    @Override
    public void promptLocationFromUser() {
        System.out.println("Enter your location in the form 'x, y'");
    }

    @Override
    public String getStringFromUser() {
        String string = scanner.nextLine();
        return string;
    }


    @Override
    public Integer getIntegerFromUser() {
        Integer type = scanner.nextInt();
        return type;
    }

    @Override
    public void showTaxiTypeChoice() {
        System.out.println("We have three types of taxis! Please select 1, 2 or 3. \nNormal Taxi (1) \nUrgent Taxi (2) \nPartyBus (3)");

    }

    @Override
    public void promptForTaxiTypeError() {
        System.out.println("Invalid selection. Please select 1, 2, or 3.");
        showTaxiTypeChoice();
    }


    @Override
    public void showRouteCancelled() {
        System.out.println("Route cancelled");
    }

    @Override
    public boolean readYesOrNoAnswer() {
        String answer = getStringFromUser();
        return answer.contains("y");
    }

    @Override
    public void showBookingInfo(double distance, String taxiTypeString, double price, String registrationNumber, int distanceToTaxi) {
        System.out.println("The destination is " + distance +"km away");
        System.out.println("the taxi will be charged using the " + taxiTypeString + " rate and will cost " + price);
        System.out.println("Your taxis reg is " + registrationNumber);
        System.out.println("It is " + distanceToTaxi + "km away");
        System.out.println("book taxi? (y/n)");
        scanner.nextLine();
    }

     @Override
     public void tellUserTaxiEnroute() {
        System.out.println("Taxi is enroute");
    }

    @Override
    public void sayTaxiHasArrived() {
        System.out.println("Your Taxi has arrived");
    }

    @Override
    public void completeRide(){
        System.out.println("Ride is completed");
    }


    @Override
    public void promptDriverRatingAgain() {
        System.out.println("Please enter a number between 1 and 5");
    }

    @Override
    public void promptDriverRating() {
        System.out.println("How many stars from 1-5 do you wish to rate your driver");
    }

    @Override
    public void showTaxiRating(double assignedTaxiRating) {
        System.out.println("Thank you for reviewing");
        System.out.println("Just so you know the average rating for your taxi is: " + assignedTaxiRating);
    }

    @Override
    public void promptAnotherBooking() {
        System.out.print("Do you want to make another booking? (y/n): ");
        scanner.nextLine();
    }

    @Override
    public void sayGoodbyeOnUserExit() {
        System.out.println("Thank you for using our app. Hope to see you soon!");
        System.exit(0);
    }

    @Override
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


    @Override
    public void printRouteMap(Map map, Location destination, DoublyLinkedList<Location> route, Location currentLocation, int height, int width) {
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
