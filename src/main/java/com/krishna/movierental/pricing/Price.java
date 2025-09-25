package com.krishna.movierental.pricing;

public abstract class Price {
    public abstract double calculateRentalCharge(int rentedDays);

    public int getFrequentRenterPoints(int rentedDays) {
        return 1;
    }
    
}
