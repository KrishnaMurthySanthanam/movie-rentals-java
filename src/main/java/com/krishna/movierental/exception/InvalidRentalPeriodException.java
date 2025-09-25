package com.krishna.movierental.exception;

public class InvalidRentalPeriodException   extends RuntimeException{
    public InvalidRentalPeriodException(int days) {
        super("Invalid rental period: " + days + " days. Rental period must be a positive integer.");
    }
}
