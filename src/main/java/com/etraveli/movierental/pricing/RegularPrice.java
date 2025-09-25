package com.etraveli.movierental.pricing;

public class RegularPrice extends Price {

    @Override
    public double calculateRentalCharge(int daysMovieRented) {
        double totalCharge = 2;
        if (daysMovieRented > 2) {
            totalCharge += (daysMovieRented - 2) * 1.5;
        }
        return totalCharge;
    }
}
