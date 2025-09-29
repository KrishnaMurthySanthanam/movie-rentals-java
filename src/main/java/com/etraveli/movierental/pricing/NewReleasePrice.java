package com.etraveli.movierental.pricing;

public class NewReleasePrice extends Price {

    private static final double DAILY_RATE = 3.0;
    private static final int BASE_DAYS = 2;
    private static final int BASE_POINTS = 2;

    @Override
    public double calculateRentalCharge(int rentedDays) {
        return rentedDays * DAILY_RATE;
    }

    @Override
    public int getFrequentRenterPoints(int rentedDays) {
        return rentedDays > BASE_DAYS ? BASE_POINTS : STANDARD_POINTS;
    }

}
