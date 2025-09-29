package com.etraveli.movierental.pricing;

public class RegularPrice extends Price {

    @Override
    public double calculateRentalCharge(int daysMovieRented) {
        return daysMovieRented <= 2 ? 2 : 2 + (daysMovieRented - 2) * 1.5;
    }
}
