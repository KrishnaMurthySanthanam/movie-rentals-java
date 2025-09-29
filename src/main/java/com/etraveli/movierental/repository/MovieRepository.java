package com.etraveli.movierental.repository;

import com.etraveli.movierental.domain.model.Movie;

import java.util.Optional;

public interface MovieRepository {

    Optional<Movie> findMovieById(String movieId);
}
