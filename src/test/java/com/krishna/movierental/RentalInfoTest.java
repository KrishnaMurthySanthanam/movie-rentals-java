package com.krishna.movierental;

import com.krishna.movierental.domain.model.Customer;
import com.krishna.movierental.domain.model.MovieRental;
import com.krishna.movierental.exception.InvalidRentalPeriodException;
import com.krishna.movierental.exception.MovieNotFoundException;
import com.krishna.movierental.repository.MovieRepository;
import com.krishna.movierental.repository.MovieRepositoryImpl;
import com.krishna.movierental.service.RentalInfo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentalInfoTest {

    private static RentalInfo rentalInfo;
    private static MovieRepository movieRepository;

    @BeforeAll
    static void setup() {
        System.setProperty("logback.configurationFile", "src/main/resources/logback.xml");
        movieRepository = new MovieRepositoryImpl();
        rentalInfo = new RentalInfo(movieRepository);
    }

    @AfterAll
    static void teardown() {
        rentalInfo = null;
        movieRepository = null;
    }

    @Test
    void givenRegularMovieRental_whenStatementCalled_thenReturnsCorrectAmount() {
        // Arrange
        String expectedResult = """
                Rental Record for John Doe
                	You've Got Mail	3.5
                Amount owed is 3.5
                You earned 1 frequent points
                """;
        Customer customer = new Customer("John Doe", List.of(
                new MovieRental("F001", 3)
        ));

        // Act
        String actualResult = rentalInfo.statement(customer);

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void givenNewReleaseMovieRental_whenStatementCalled_thenReturnsCorrectAmount() {
        // Arrange
        String expectedResult = """
                Rental Record for John Doe
                	Fast & Furious X	12.0
                Amount owed is 12.0
                You earned 2 frequent points
                """;

        Customer newReleasecustomer = new Customer("John Doe", List.of(
                new MovieRental("F004", 4)
        ));

        // Act
        String actualResult = rentalInfo.statement(newReleasecustomer);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void givenChildrenMovieRental_whenStatementCalled_thenReturnsCorrectAmount() {
        // Arrange
        String expectedResult = """
                Rental Record for Alice
                	Cars	4.5
                Amount owed is 4.5
                You earned 1 frequent points
                """;

        Customer customer = new Customer("Alice", List.of(
                new MovieRental("F003", 5)
        ));

        // Act
        String actualResult = rentalInfo.statement(customer);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void givenMultipleRentals_whenStatementCalled_thenHandlesAllRentalsCorrectly() {
        // Arrange
        String expectedResult = """
                Rental Record for Mike
                	You've Got Mail	2.0
                	Cars	1.5
                	Fast & Furious X	12.0
                Amount owed is 15.5
                You earned 4 frequent points
                """;

        Customer customer = new Customer("Mike", List.of(
                new MovieRental("F001", 1),
                new MovieRental("F003", 3),
                new MovieRental("F004", 4)
        ));

        // Act
        String actualResult = rentalInfo.statement(customer);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void givenNoRentals_whenStatementCalled_thenReturnsZeroAmounts() {
        // Arrange
        String expectedResult = """
                Rental Record for Bob
                Amount owed is 0.0
                You earned 0 frequent points
                """;

        Customer customer = new Customer("Bob", List.of());

        // Act
        String actualResult = rentalInfo.statement(customer);

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void givenNewReleaseRentalForTwoDaysOrLess_whenStatementCalled_thenNoBonusPoints() {
        // Arrange
        String expectedResult = """
                Rental Record for Rob
                	Fast & Furious X	6.0
                Amount owed is 6.0
                You earned 1 frequent points
                """;

        Customer customer = new Customer("Rob", List.of(
                new MovieRental("F004", 2)));

        // Act
        String actualResult = rentalInfo.statement(customer);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void givenNullCustomerName_whenStatementCalled_thenHandlesGracefully() {
        // Arrange
        Customer customer = new Customer(null, List.of(
                new MovieRental("F003", 2)
        ));

        // Act
        String result = rentalInfo.statement(customer);

        // Assert
        assertTrue(result.contains("Rental Record for Unknown Customer"));
    }

    @Test
    void givenNegativeRentalDays_whenStatementCalled_thenThrowsInvalidRentalPeriodException() {
        Exception ex = assertThrows(InvalidRentalPeriodException.class, this::createCustomerWithZeroRentalDays);
        assertEquals("Invalid rental period: -2 days. Rental period must be a positive integer.", ex.getMessage());
    }

    @Test
    void givenUnknownMovieId_whenStatementCalled_thenThrowsMovieNotFoundException() {
        MovieNotFoundException ex = assertThrows(MovieNotFoundException.class, this::invokeStatementWithUnknownMovieId);
        assertEquals("Movie not found with ID: UNKNOWN_ID", ex.getMessage());
    }

    private void createCustomerWithZeroRentalDays() {
        Customer customer = new Customer("Micheal", List.of(
                new MovieRental("F001", -2)
        ));
        rentalInfo.statement(customer);
    }

    private void invokeStatementWithUnknownMovieId() {
        Customer customer = new Customer("Eva", List.of(
                new MovieRental("UNKNOWN_ID", 3)
        ));
        rentalInfo.statement(customer);
    }


}