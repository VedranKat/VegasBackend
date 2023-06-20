package com.example.vegasBackend.util;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*
 * This class is used to quickly
 * generate data for testing purposes
 */
@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bootStrapReminder();


        //generateData();
    }

    private void generateData(){
        //generateUsers();
        //generateTickets();
        //generateOdds();
    }

    private void bootStrapReminder(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!----------------------------------------!");
        System.out.println("!----------- BOOTSTRAP ACTIVE! ----------!");
        System.out.println("!----------------------------------------!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
