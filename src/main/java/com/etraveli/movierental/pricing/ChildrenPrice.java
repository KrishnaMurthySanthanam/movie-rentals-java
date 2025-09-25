package com.etraveli.movierental.pricing;

public class ChildrenPrice extends Price {

    @Override
    public double calculateRentalCharge(int daysMovieRented) {
        double totalCharge = 1.5;
        if (daysMovieRented > 3) {
            totalCharge += (daysMovieRented - 3) * 1.5;
        }
        return totalCharge;
    }

}
