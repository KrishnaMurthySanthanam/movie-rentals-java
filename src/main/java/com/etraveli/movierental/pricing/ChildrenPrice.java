package com.etraveli.movierental.pricing;

public class ChildrenPrice extends Price {

    @Override
    public double calculateRentalCharge(int daysMovieRented) {
        return daysMovieRented <= 3 ? 1.5 : 1.5 + (daysMovieRented - 3) * 1.5;
    }

}
