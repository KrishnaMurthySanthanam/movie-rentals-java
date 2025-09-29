package com.etraveli.movierental.pricing;

public abstract class Price {
    
    static final int STANDARD_POINTS = 1;

    public abstract double calculateRentalCharge(int rentedDays);

    public int getFrequentRenterPoints(int rentedDays) {
        return STANDARD_POINTS;
    }
    
}
