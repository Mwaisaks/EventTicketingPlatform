package com.devtiro.EventTicketingPlatform.service.impl;

import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;
import com.devtiro.EventTicketingPlatform.domain.entity.TicketType;
import com.devtiro.EventTicketingPlatform.domain.entity.User;
import com.devtiro.EventTicketingPlatform.domain.enums.EventStatusEnum;
import com.devtiro.EventTicketingPlatform.domain.enums.TicketStatusEnum;
import com.devtiro.EventTicketingPlatform.exceptions.TicketTypeNotFoundException;
import com.devtiro.EventTicketingPlatform.exceptions.TicketsSoldOutException;
import com.devtiro.EventTicketingPlatform.repository.TicketRepository;
import com.devtiro.EventTicketingPlatform.repository.TicketTypeRepository;
import com.devtiro.EventTicketingPlatform.repository.UserRepository;
import com.devtiro.EventTicketingPlatform.service.QrCodeService;
import com.devtiro.EventTicketingPlatform.service.TicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;
    private final QrCodeService qrCodeService;

    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with ID %s not found", userId)
                ));

        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId)
                .orElseThrow(() -> new TicketTypeNotFoundException(
                        String.format("Ticket Type with ID %s not found", ticketTypeId)
                ));

        //If two users call this at the same time, one will have to wait as the other one carries on
        //Is this mot Throttling in REST APIs? Is this not what Apache kafka is supposed to do

        int purchasedTickets = ticketRepository.countByTicketTypeId(ticketTypeId);
        Integer totalAvailable = ticketType.getTotalAvailable();

        if (purchasedTickets + 1 > totalAvailable){
            throw new TicketsSoldOutException();
        }

        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(user);

        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);

        return ticketRepository.save(savedTicket);
    }
}
