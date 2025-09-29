package com.etraveli.movierental.repository;

import com.etraveli.movierental.domain.model.Movie;
import com.etraveli.movierental.domain.model.MovieCategory;

import java.util.Map;
import java.util.Optional;

public class MovieRepositoryImpl implements MovieRepository {

    private final Map<String, Movie> movies;

    public MovieRepositoryImpl() {
        movies = Map.of(
                "F001", new Movie( "You've Got Mail", MovieCategory.REGULAR),
                "F002", new Movie( "Matrix", MovieCategory.REGULAR),
                "F003", new Movie( "Cars", MovieCategory.CHILDREN),
                "F004", new Movie( "Fast & Furious X", MovieCategory.NEW_RELEASE)
        );
    }

    @Override
    public Map<String, Movie> getMovies() {
        return movies;
    }

    @Override
    public Optional<Movie> findMovieById(String movieId) {
        return Optional.ofNullable(movies.get(movieId));
    }

}
