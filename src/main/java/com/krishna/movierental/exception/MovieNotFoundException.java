package com.krishna.movierental.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String movieId) {
        super("Movie not found with ID: " + movieId);
    }

}
