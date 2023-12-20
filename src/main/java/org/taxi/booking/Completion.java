package org.taxi.booking;

import org.taxi.taxi.Taxi;

public class Completion {
    private final Taxi taxi;
    private final int rating;

    public Completion(Taxi taxi, int rating) {
        this.taxi = taxi;
        this.rating = rating;
        double oldRating = getTaxiRating();
        setRating(oldRating);
    }

    public double getTaxiRating() {
        double oldRating = taxi.getRating() * taxi.getTotalRatings();
        return oldRating;
    }

    public void setRating(double taxiRating) {
        taxiRating += rating;
        taxi.setTotalRatings(taxi.getTotalRatings() + 1);
        taxi.setRating(taxiRating / taxi.getTotalRatings());
    }
}
