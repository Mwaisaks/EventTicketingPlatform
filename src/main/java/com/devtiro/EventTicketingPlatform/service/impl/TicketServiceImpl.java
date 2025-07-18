package com.devtiro.EventTicketingPlatform.service.impl;

import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;
import com.devtiro.EventTicketingPlatform.repository.TicketRepository;
import com.devtiro.EventTicketingPlatform.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Page<Ticket> listTicketsForUser(UUID userId, Pageable pageable) {
        return ticketRepository.findByPurchaserId(userId, pageable);
    }

    @Override
    public Optional<Ticket> getTicketForUser(UUID ticketId, UUID userId) {
        return ticketRepository.findByIdAndPurchaserId(ticketId, userId);
    }
}
