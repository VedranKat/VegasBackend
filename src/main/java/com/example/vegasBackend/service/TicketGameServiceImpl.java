package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.response.GameResponse;
import com.example.vegasBackend.dto.response.TicketGameResponse;
import com.example.vegasBackend.exception.EntityNotFoundException;
import com.example.vegasBackend.model.Ticket;
import com.example.vegasBackend.model.TicketGame;
import com.example.vegasBackend.repository.api.TicketGameRepository;
import com.example.vegasBackend.repository.api.TicketRepository;
import com.example.vegasBackend.service.api.TicketGameService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketGameServiceImpl implements TicketGameService {

    private final TicketGameRepository ticketGameRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<TicketGameResponse> findAllByTicketId(Long id) throws EntityNotFoundException {

        List<TicketGame> ticketGames = ticketGameRepository.findAllByTicketId(id);

        return ticketGames.stream()
                .map(ticketGame -> {
                    TicketGameResponse ticketGameResponse = mapper.map(ticketGame, TicketGameResponse.class);
                    ticketGameResponse.setGameResponse(mapper.map(ticketGame.getGame(), GameResponse.class));
                    return ticketGameResponse;
                })
                .toList();
    }
}
