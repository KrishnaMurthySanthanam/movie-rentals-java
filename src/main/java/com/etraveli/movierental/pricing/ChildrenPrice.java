package com.etraveli.movierental.pricing;

public class ChildrenPrice extends Price {
    
    private static final double BASE_CHARGE = 1.5;
    private static final int BASE_DAYS = 3;
    private static final double EXTRA_DAY_RATE = 1.5;

    @Override
    public double calculateRentalCharge(int daysMovieRented) {
        if (daysMovieRented <= BASE_DAYS) {
        return BASE_CHARGE;
    }

    int extraDays = daysMovieRented - BASE_DAYS;
    return BASE_CHARGE + extraDays * EXTRA_DAY_RATE;
    
    }
}
