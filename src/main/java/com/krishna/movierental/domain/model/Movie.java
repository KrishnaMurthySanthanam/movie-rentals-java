package com.krishna.movierental.domain.model;

import com.krishna.movierental.pricing.ChildrenPrice;
import com.krishna.movierental.pricing.NewReleasePrice;
import com.krishna.movierental.pricing.Price;
import com.krishna.movierental.pricing.RegularPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Movie {
    private static final Logger logger = LoggerFactory.getLogger(Movie.class);

    private final String id;
    private final String title;
    private Price price;

    public Movie(String id, String title, MovieCategory category) {
        this.id = id;
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
