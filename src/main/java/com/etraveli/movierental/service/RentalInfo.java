package com.etraveli.movierental.service;

import com.etraveli.movierental.domain.dto.MovieRentalDetail;
import com.etraveli.movierental.domain.dto.RentalSummary;
import com.etraveli.movierental.domain.model.Customer;
import com.etraveli.movierental.domain.model.Movie;
import com.etraveli.movierental.domain.model.MovieRental;
import com.etraveli.movierental.exception.MovieNotFoundException;
import com.etraveli.movierental.repository.MovieRepository;

import java.util.Optional;

public class RentalInfo {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RentalInfo.class);
    private final MovieRepository movieRepository;

    public RentalInfo(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String statement(Customer customer) {
        logger.info("Generating statement for customer '{}'", customer.name());

        RentalSummary summary = new RentalSummary();
        for (MovieRental rental : customer.rentals()) {
            Movie movie = movieRepository.findMovieById(rental.movieId())
                    .orElseThrow(() -> {
                        logger.error("Movie ID '{}' not found for customer '{}'", rental.movieId(), customer.name());
                        return new MovieNotFoundException(rental.movieId());
                    });

            double amount = movie.calculateMovieCharge(rental.rentalDays());
            int points = movie.getFrequentRenterPoints(rental.rentalDays());

            logger.debug("Processed rental: '{}' for {} days → {} ", movie.getTitle(), rental.rentalDays(), amount);

            summary.addRental(new MovieRentalDetail(movie.getTitle(), amount, points));
        }

        logger.info("Total amount: {}, Frequent renter points: {}", summary.getTotalAmount(), summary.getTotalPoints());

        return buildTextStatement(customer.name(), summary);
    }

    private String buildTextStatement(String customerName, RentalSummary summary) {
        String name = Optional.ofNullable(customerName).orElse("Unknown Customer");

        StringBuilder result = new StringBuilder();
        result.append(String.format("Rental Record for %s%n", name));

        summary.getRentals().forEach(rental ->  {
            result.append(String.format("\t%s\t%.1f%n", rental.title(), rental.amount()));
        });

        result.append(String.format("Amount owed is %.1f%n", summary.getTotalAmount()));
        result.append(String.format("You earned %d frequent points%n", summary.getTotalPoints()));

        return result.toString();
    }

}

