package org.taxi.userinterface.commandline;

import org.taxi.booking.Scheduler;
import org.taxi.datastructure.DoublyLinkedList;
import org.taxi.map.GridMap;
import org.taxi.map.Location;
import org.taxi.map.Map;

public interface UserInterface {
    static CommandLine getCommandLine() {
        return CommandLine.commandLine;
    }

    Scheduler getScheduler();

    void setScheduler(Scheduler scheduler);

    void clearScreen();

    void showWelcomeScreen();

    void promptDestinationFromUser();

    void promptLocationFromUser();

    String getStringFromUser();

    Integer getIntegerFromUser();

    void showTaxiTypeChoice();

    void promptForTaxiTypeError();

    void showRouteCancelled();

    boolean readYesOrNoAnswer();

    void showBookingInfo(double distance, String taxiTypeString, double price, String registrationNumber, int distanceToTaxi);

    void tellUserTaxiEnroute();

    void sayTaxiHasArrived();

    void completeRide();

    void promptDriverRatingAgain();

    void promptDriverRating();

    void showTaxiRating(double assignedTaxiRating);

    void promptAnotherBooking();

    void sayGoodbyeOnUserExit();

    void showRectangularMap(GridMap map, int height, int width);

    void printRouteMap(Map map, Location destination, DoublyLinkedList<Location> route, Location currentLocation, int height, int width);
}
