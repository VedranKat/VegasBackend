package com.example.vegasBackend.util;

import com.example.vegasBackend.model.Sport;
import com.example.vegasBackend.repository.api.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*
 * This class is used to quickly
 * generate data for testing purposes
 */
@Component
@RequiredArgsConstructor
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final SportRepository sportRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        generateData();

    }

    private void generateData(){
        bootStrapReminder();
        generateSports();

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

    private void generateSports(){

        System.out.println("Generating Sports...");

        Sport mlb = new Sport();
        mlb.setKey("baseball_mlb");
        mlb.setTitle("MLB");
        mlb.setDescription("Major League Baseball");
        sportRepository.save(mlb);

        Sport cfl = new Sport();
        cfl.setKey("americanfootball_cfl");
        cfl.setTitle("CFL");
        cfl.setDescription("Canadian Football League");
        sportRepository.save(cfl);

        Sport mls = new Sport();
        mls.setKey("soccer_usa_mls");
        mls.setTitle("MLS");
        mls.setDescription("Major League Soccer");
        sportRepository.save(mls);





    }
}
