package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.request.GameRequest;
import com.example.vegasBackend.dto.response.gameResponseApi.GameResponseApi;
import com.example.vegasBackend.service.api.GameService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public List<GameResponseApi> getOdds(String sportKey) {

        //TODO: Improve error handling
        try {
            // Create an instance of ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Make the API call to retrieve the JSON response
            //TODO: change the api url to use the sportKey parameter later
            String apiUrl = "https://api.the-odds-api.com/v4/sports/baseball_mlb/odds/?regions=eu&markets=h2h&apiKey="+System.getenv("ODDS_API_KEY");
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the JSON response into a String
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();

            // Deserialize the JSON response into a list of GameRequest objects
            List<GameResponseApi> gameResponses = objectMapper.readValue(jsonContent.toString(), new TypeReference<List<GameResponseApi>>() {});

            //TODO: Save the list of GameRequest objects to the database
            for (GameResponseApi gameResponse : gameResponses) {
                System.out.println(gameResponse);
            }
            // Return the deserialized list of GameRequest objects
            return gameResponses;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // TODO: Improve error handling
        }
    }
}
