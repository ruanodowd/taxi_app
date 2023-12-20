package org.taxi.userinterface.commandline;

import org.taxi.booking.Booking;
import org.taxi.booking.Completion;
import org.taxi.booking.Scheduler;
import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;
import org.taxi.taxi.NormalTaxi;
import org.taxi.taxi.PartyBusTaxi;
import org.taxi.taxi.Taxi;
import org.taxi.taxi.UrgentTaxi;

import java.util.function.Predicate;

public class Controller {
    //singleton pattern
    private static Controller instance = new Controller();
    private CommandLine cli;
    private Controller() {
    }
    private Map map;
    private Scheduler scheduler;
    public static Controller getInstance() {
        return instance;
    }

    public void setUp(){
        this.map = new GridMap(12,12);
        this.scheduler = new Scheduler(map);
        this.cli = UserInterface.getCommandLine();
        cli.setScheduler(scheduler);
        Taxi taxi = new PartyBusTaxi("RAWR - P6");
        map.getLocation(6,6).addTaxi(taxi);
        Taxi partyBus = new PartyBusTaxi("P0");
        map.getLocation(0,0).addTaxi(partyBus);
        Taxi taxi2 = new NormalTaxi("N6");
        map.getLocation(6,6).addTaxi(taxi2);
        Taxi NormalTaxi = new NormalTaxi("N0");
        map.getLocation(0,0).addTaxi(NormalTaxi);
        Taxi taxi3 = new UrgentTaxi("U6");
        map.getLocation(6,6).addTaxi(taxi3);
        Taxi UrgentTaxi = new UrgentTaxi("U0");
        map.getLocation(0,0).addTaxi(UrgentTaxi);

    }

    public void setCli(CommandLine cli) {
        this.cli = cli;
    }

    public void startProgram(){
        cli.showWelcomeScreen();
        userLocationInputScreen();
    }
    public void userBookingConfirmationScreen(Location location, Location destination, Predicate<Taxi> taxiType){
        Map map = scheduler.getMap();
        Booking booking = new Booking(map, location, destination);
        cli.clearScreen();
        scheduler.addBooking(booking, taxiType);
        double distance = booking.getDistance();
        String taxiTypeString = booking.getTaxi().getRate().getTaxiRateType();
        double price = booking.getPrice();
        String registrationNumber = booking.getTaxi().getRegistrationNumber();
        int distanceToTaxi = booking.getTaxi().getLocation(map).getDistance();
        cli.showBookingInfo(distance, taxiTypeString, price, registrationNumber, distanceToTaxi);
        boolean answer = cli.readYesOrNoAnswer();
        if (answer){
            showTaxiEnrouteDisplay(booking);
            showTravellingDisplay(booking);
            cli.completeRide();
            rateDriver();
        } else {
            cli.showRouteCancelled();
            scheduler.cancelBooking(booking);
        }
    }
    public void userLocationInputScreen(){
        cli.clearScreen();
        cli.promptLocationFromUser();
        String locationString = cli.getStringFromUser();
        cli.promptDestinationFromUser();
        String destinationString = cli.getStringFromUser();
        Location from = processCoordinateString(locationString);
        Location destination = processCoordinateString(destinationString);

        Predicate<Taxi> taxiType = promptForTaxiType();

        userBookingConfirmationScreen(from, destination, taxiType);
    }
    public void endProgramme() {
        cli.clearScreen();
        cli.promptAnotherBooking();

        if (cli.readYesOrNoAnswer()){
            cli.clearScreen();
            userLocationInputScreen();
        } else{
            cli.sayGoodbyeOnUserExit();
        }

    }

    public Scheduler getScheduler() {
        return scheduler;
    }
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    Predicate<Taxi> promptForTaxiType() {//move to controller
        // asking what type of taxi you want
        Predicate<Taxi> taxiType = null;
        cli.showTaxiTypeChoice();

        while (taxiType == null) {
            Integer type = cli.getIntegerFromUser();

            if (type == 1) {
                taxiType = taxi -> taxi instanceof NormalTaxi;
            } else if (type == 2) {
                taxiType = taxi -> taxi instanceof UrgentTaxi;
            } else if (type == 3) {
                taxiType = taxi -> taxi instanceof PartyBusTaxi;
            } else {
                cli.promptForTaxiTypeError();
            }
        }
        return taxiType;
    }

    public void showTaxiEnrouteDisplay(Booking booking){
        cli.tellUserTaxiEnroute();
        try {
            DoublyLinkedList<Location> route = booking.getTaxi()
                    .getLocation(scheduler.getMap())
                    .getPathway();
            route.add(booking.getTaxi().getLocation(booking.getMap()));
            route = route.reverse();
            showIterativeRouteMap(scheduler.getMap(), booking.getCustomerLocation(), route, booking);
        } catch (InterruptedException e) {
            System.out.println("ERROR ENROUTE");
            throw new RuntimeException(e);
        }
    }

    public void showTravellingDisplay(Booking booking){
        cli.sayTaxiHasArrived();
        try {
            Thread.sleep(1000);
            DoublyLinkedList<Location> route = booking.getDestination().getPathway();
            route.add(booking.getDestination());
            showIterativeRouteMap(scheduler.getMap(), booking.getDestination(), route, booking);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void rateDriver(){
        cli.promptDriverRating();
        try {
            Integer rating = cli.getIntegerFromUser();
            if (rating > 5 || rating <= 0){
                throw new IllegalArgumentException("Womp Womp");
            }
            Taxi assignedTaxi = scheduler.getBookings().get(0).getTaxi();
            new Completion(assignedTaxi, rating);
            double assignedTaxiRating = assignedTaxi.getRating();
            cli.showTaxiRating(assignedTaxiRating);
            Controller.getInstance().endProgramme();

        } catch (Exception e){
            cli.promptDriverRatingAgain();
            rateDriver();
        }
    }
    public void showIterativeRouteMap(Map map, Location destination, DoublyLinkedList<Location> route, Booking booking) throws InterruptedException {
        int height = map.getHeight();
        int width = map.getWidth();
        for (Location currentLocation : route){
            currentLocation.setCovered(false);
        }
        for (Location currentLocation : route
        ) {
            Thread.sleep(booking.getTaxi().getSpeed());
            for (int i = 0; i < 10; i++) {
                System.out.println();
            }
            cli.printRouteMap(map, destination, route, currentLocation, height, width);
        }
    }
    public Location processCoordinateString(String unprocessedCoordinates){
        String[] split = unprocessedCoordinates.split("\\D");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[split.length-1]);
        return scheduler.getMap().getLocation(x, y);
    }

    public Map getMap() {
        return map;
    }
    public void setMap(Map map) {
        this.map = map;
    }

}
