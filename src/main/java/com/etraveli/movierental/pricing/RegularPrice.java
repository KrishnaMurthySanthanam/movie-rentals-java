package com.etraveli.movierental.pricing;

public class RegularPrice extends Price {

    private static final double BASE_CHARGE = 2.0;
    private static final int BASE_DAYS = 2;
    private static final double EXTRA_DAY_RATE = 1.5;

    @Override
    public double calculateRentalCharge(int daysMovieRented) {
        return daysMovieRented <= BASE_DAYS ? BASE_CHARGE : BASE_CHARGE + (daysMovieRented - BASE_DAYS) * EXTRA_DAY_RATE;
    }
}
