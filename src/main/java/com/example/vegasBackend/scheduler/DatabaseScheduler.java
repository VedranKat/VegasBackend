package com.example.vegasBackend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseScheduler {

    public void validateTickets() {
        //Validate tickets
        //Call this in OddsApiScheduler.java maybe?
        System.out.println("Validating tickets...");
    }

    //@Scheduled(fixedDelay = 3 * 24 * 60 * 60 * 1000) // Run every 3 days
    public void deleteOldGames() {
        //Delete old games that have never been bet on
        System.out.println("Deleting old games...");
    }

}
