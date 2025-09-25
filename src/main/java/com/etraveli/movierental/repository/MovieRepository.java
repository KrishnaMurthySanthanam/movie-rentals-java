package com.etraveli.movierental.repository;

import com.etraveli.movierental.domain.model.Movie;

import java.util.Map;
import java.util.Optional;

public interface MovieRepository {
    Map<String, Movie> getMovies();

    Optional<Movie> findMovieById(String movieId);
}
