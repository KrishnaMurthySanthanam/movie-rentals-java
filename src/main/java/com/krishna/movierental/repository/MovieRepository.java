package com.krishna.movierental.repository;

import com.krishna.movierental.domain.model.Movie;

import java.util.Map;
import java.util.Optional;

public interface MovieRepository {
    Map<String, Movie> getMovies();

    Optional<Movie> findMovieById(String movieId);
}
