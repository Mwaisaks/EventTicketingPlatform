package com.devtiro.EventTicketingPlatform.controller;

import com.devtiro.EventTicketingPlatform.domain.dto.response.GetTicketResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.ListTicketResponseDto;
import com.devtiro.EventTicketingPlatform.mappers.TicketMapper;
import com.devtiro.EventTicketingPlatform.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.devtiro.EventTicketingPlatform.util.JwtUtil.parseUserId;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/tickets")
public class TicketController {

    private final TicketMapper ticketMapper;
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<Page<ListTicketResponseDto>> listTickets(
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable
            ){

        Page<ListTicketResponseDto> tickets = ticketService.listTicketsForUser(
                parseUserId(jwt), pageable
        ).map(ticketMapper::toListTicketResponseDto);

        return ResponseEntity.ok(tickets);
    }

    public ResponseEntity<GetTicketResponseDto> getTicket(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID ticketId
    ){
        return ticketService.getTicketForUser(
                parseUserId(jwt), ticketId)
                .map(ticketMapper::toGetTicketResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
