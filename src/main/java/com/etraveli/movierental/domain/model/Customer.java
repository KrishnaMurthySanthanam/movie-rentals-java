package com.etraveli.movierental.domain.model;

import java.util.List;

public record Customer(String name, List<MovieRental> rentals) {
}