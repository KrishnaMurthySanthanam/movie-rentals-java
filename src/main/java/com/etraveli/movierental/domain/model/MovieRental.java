package com.etraveli.movierental.domain.model;

import com.etraveli.movierental.exception.InvalidRentalPeriodException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record MovieRental(String movieId, int rentalDays) {
    private static final Logger logger = LoggerFactory.getLogger(MovieRental.class);

    public MovieRental {
        if (rentalDays <= 0) {
            logger.error("Invalid rental days: {}", rentalDays);
            throw new InvalidRentalPeriodException(rentalDays);
        }
    }

}