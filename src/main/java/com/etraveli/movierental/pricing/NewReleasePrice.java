package com.etraveli.movierental.pricing;

public class NewReleasePrice extends Price {

    @Override
    public double calculateRentalCharge(int rentedDays) {
        return rentedDays * 3;
    }

    @Override
    public int getFrequentRenterPoints(int rentedDays) {
        return (rentedDays > 2) ? 2 : 1;
    }

}
