package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.request.GameRequest;
import com.example.vegasBackend.dto.request.TicketGameRequest;
import com.example.vegasBackend.dto.request.TicketRequest;
import com.example.vegasBackend.dto.response.TicketResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.exception.GameUnavailableException;
import com.example.vegasBackend.exception.InsufficientBalanceException;
import com.example.vegasBackend.model.Game;
import com.example.vegasBackend.model.Ticket;
import com.example.vegasBackend.model.TicketGame;
import com.example.vegasBackend.model.User;
import com.example.vegasBackend.repository.api.GameRepository;
import com.example.vegasBackend.repository.api.TicketGameRepository;
import com.example.vegasBackend.repository.api.TicketRepository;
import com.example.vegasBackend.repository.api.UserRepository;
import com.example.vegasBackend.service.api.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final TicketGameRepository ticketGameRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public TicketResponse create(TicketRequest ticketRequest) throws EntityNotFoundException {

        User user = userRepository.findByEmail(ticketRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "User with email " + ticketRequest.getEmail() + " not found"));

        BigDecimal price = ticketRequest.getPrice();

        if (user.getBalance().compareTo(price) < 0) {
            throw new InsufficientBalanceException("Not enough money");
        }

        List<GameRequest> games = ticketRequest.getGames();
        List<TicketGameRequest> ticketGames = new ArrayList<>();

        //Check if the games exist and are available for betting, and save them to the ticketGames list
        this.validateGames(games, ticketGames);

        //Save ticket
        Ticket savedTicket = ticketRepository.save(Ticket.builder()
                .user(user)
                .price(price)
                .winAmount(ticketRequest.getWinAmount())
                .build());

        //Save data to link table
        ticketGames.forEach(ticketGameRequest -> {
            ticketGameRequest.setTicket(savedTicket);
            ticketGameRepository.save(mapper.map(ticketGameRequest, TicketGame.class));
        });

        //Update user balance
        user.setBalance(user.getBalance().subtract(price));
        userRepository.save(user);

        TicketResponse ticketResponse = mapper.map(savedTicket, TicketResponse.class);
        ticketResponse.setEmail(user.getEmail());

        return ticketResponse;
    }

    private void validateGames(List<GameRequest> games, List<TicketGameRequest> ticketGames) throws EntityNotFoundException {
        //Check if the games are valid, and save them to the ticketGames list
        for (GameRequest gameRequest: games){
            Game game = gameRepository.findByGameApiId(gameRequest.getGameApiId())
                    .orElseThrow(() -> new EntityNotFoundException("" +
                            "Game with id " + gameRequest.getGameApiId() + " not found"));

            Date gameDate = game.getCommenceTime();
            //compare gameDate with current time and add one two hours for safety
            if(gameDate.compareTo(new Date(System.currentTimeMillis() + 7200000)) < 0){
                throw new GameUnavailableException(
                        "Game with id " + gameRequest.getGameApiId() + " cannot be played");
            }

            TicketGameRequest ticketGameRequest = new TicketGameRequest();
            ticketGameRequest.setGame(game);
            ticketGameRequest.setChosenWinner(gameRequest.getChosenWinner());
            ticketGames.add(ticketGameRequest);
        }
    }

    @Override
    public List<TicketResponse> getAllByUserEmail(String email) {
        return null;
    }

    @Override
    public void checkTicket(Long id) {

    }
}
