package com.etraveli.movierental.domain.model;

import com.etraveli.movierental.pricing.ChildrenPrice;
import com.etraveli.movierental.pricing.NewReleasePrice;
import com.etraveli.movierental.pricing.Price;
import com.etraveli.movierental.pricing.RegularPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Movie {
    private static final Logger logger = LoggerFactory.getLogger(Movie.class);

    private final String title;
    private Price price;

    public Movie(String id, String title, MovieCategory category) {
        this.title = title;
        setPriceCode(category);
    }

    public String getTitle() {
        return title;
    }

    void setPriceCode(MovieCategory category) {
        switch (category) {
            case REGULAR -> price = new RegularPrice();
            case CHILDREN -> price = new ChildrenPrice();
            case NEW_RELEASE -> price = new NewReleasePrice();
            default -> throw new IllegalArgumentException("Invalid category: " + category);
        }
    }

    public double calculateMovieCharge(int daysMovieRented) {
        double charge = price.calculateRentalCharge(daysMovieRented);
        logger.debug("Calculating charge for movie '{}': {} days → {} SEK", title, daysMovieRented, charge);
        return charge;
    }

    public int getFrequentRenterPoints(int daysMovieRented) {
        int points = price.getFrequentRenterPoints(daysMovieRented);
        logger.debug("Calculating frequent renter points for movie '{}': {} days → {} points", title, daysMovieRented, points);
        return points;
    }

}
