package com.etraveli.movierental.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class RentalSummary {
    private final List<MovieRentalDetail> rentals = new ArrayList<>();
    private double totalAmount = 0;
    private int totalPoints = 0;

    public void addRental(MovieRentalDetail line) {
        rentals.add(line);
        totalAmount += line.amount();
        totalPoints += line.points();
    }

    public List<MovieRentalDetail> getRentals() {
        return rentals;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

}