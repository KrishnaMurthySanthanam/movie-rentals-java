package com.etraveli.movierental.pricing;

public class ChildrenPrice extends Price {
    
    private static final double BASE_CHARGE = 1.5;
    private static final int BASE_DAYS = 3;

    @Override
    public double calculateRentalCharge(int daysMovieRented) {
        return daysMovieRented <= BASE_DAYS ? BASE_CHARGE : BASE_CHARGE + (daysMovieRented - BASE_DAYS) * BASE_CHARGE;
    }

}
