package com.example.vegasBackend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OddsApiScheduler {

    //@Scheduled(cron = "0 */2 * * * *") //TODO: change to 0 0 */6 * * * to run every 6 hours
    public void getOdds() {
        //Call Odds API to update odds
        System.out.println("Getting odds..." + new Date());
    }

    //@Scheduled(cron = "0 */2 * * * *") //TODO: change to 0 0 */6 * * * to run every 6 hours
    public void getScores() {
        //Call Odds API to update scores
        System.out.println("Getting scores..." + new Date());
        //Maybe call validateTickets() here?
    }



}
