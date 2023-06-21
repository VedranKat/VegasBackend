package com.example.vegasBackend.service;

import com.example.vegasBackend.dto.request.TicketRequest;
import com.example.vegasBackend.dto.response.TicketResponse;
import com.example.vegasBackend.repository.api.TicketRepository;
import com.example.vegasBackend.repository.api.UserRepository;
import com.example.vegasBackend.service.api.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    @Override
    public TicketResponse create(TicketRequest ticketRequest) {
        return null;
    }

    @Override
    public List<TicketResponse> getAllByUserEmail(String email) {
        return null;
    }

    @Override
    public void checkTicket(Long id) {

    }
}
