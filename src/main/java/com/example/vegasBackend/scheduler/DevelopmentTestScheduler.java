package com.example.vegasBackend.scheduler;

import org.springframework.stereotype.Component;

/**
 * This class is used to test the app in development mode.
 * It is not used in production.
 */
@Component
public class DevelopmentTestScheduler {

    public void getOddsFromApi() {
        //Get odds from API
        System.out.println("Getting odds from API...");
    }

    public void getScoresFromApi() {
        //Get scores from API
        System.out.println("Getting scores from API...");
    }

    public void validateTickets() {
        //Validate tickets
        System.out.println("Validating tickets...");
    }

    public void deleteOldGames() {
        //Delete old games that have never been bet on
        System.out.println("Deleting old games...");
    }
}
