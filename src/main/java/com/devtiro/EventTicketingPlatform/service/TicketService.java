package com.devtiro.EventTicketingPlatform.service;

import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TicketService {

    Page<Ticket> listTicketsForUser(UUID userId, Pageable pageable);
}
