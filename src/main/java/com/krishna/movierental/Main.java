package com.krishna.movierental;

import com.krishna.movierental.domain.model.Customer;
import com.krishna.movierental.domain.model.MovieRental;
import com.krishna.movierental.repository.MovieRepository;
import com.krishna.movierental.repository.MovieRepositoryImpl;
import com.krishna.movierental.service.RentalInfo;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String expectedResult = """
                Rental Record for Rob
                	You've Got Mail	3.5
                	Matrix	2.0
                Amount owed is 5.5
                You earned 2 frequent points
                """;

        MovieRepository movieRepository = new MovieRepositoryImpl();
        RentalInfo rentalInfo = new RentalInfo(movieRepository);

        String actualResult = rentalInfo.statement(new Customer("Rob",
                List.of(new MovieRental("F001", 3),
                        new MovieRental("F002", 1))));

        if (!actualResult.equals(expectedResult)) {
            String errorMessage = String.join(System.lineSeparator(),
                    "Assertion failed!",
                    "Expected:",
                    expectedResult,
                    "Got:",
                    actualResult);
            throw new AssertionError(errorMessage);
        }

        System.out.println("Success: Text Statement matches!");
    }
}
