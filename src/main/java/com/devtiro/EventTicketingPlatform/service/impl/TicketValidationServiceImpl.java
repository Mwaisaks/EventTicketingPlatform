package com.devtiro.EventTicketingPlatform.service.impl;

import com.devtiro.EventTicketingPlatform.domain.entity.QrCode;
import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;
import com.devtiro.EventTicketingPlatform.domain.entity.TicketValidation;
import com.devtiro.EventTicketingPlatform.domain.enums.QrCodeStatusEnum;
import com.devtiro.EventTicketingPlatform.domain.enums.TicketValidationMethodEnum;
import com.devtiro.EventTicketingPlatform.domain.enums.TicketValidationStatusEnum;
import com.devtiro.EventTicketingPlatform.exceptions.QrCodeNotFoundException;
import com.devtiro.EventTicketingPlatform.exceptions.TicketNotFoundException;
import com.devtiro.EventTicketingPlatform.repository.QrCodeRepository;
import com.devtiro.EventTicketingPlatform.repository.TicketRepository;
import com.devtiro.EventTicketingPlatform.repository.TicketValidationRepository;
import com.devtiro.EventTicketingPlatform.service.TicketValidationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketValidationServiceImpl implements TicketValidationService {

    private final TicketValidationRepository ticketValidationRepository;
    private final QrCodeRepository qrCodeRepository;
    private final TicketRepository ticketRepository;

    @Override
    public TicketValidation validateTicketByQrCode(UUID qrCodeId) {

        QrCode qrCode = qrCodeRepository.findByIdAndStatus(qrCodeId, QrCodeStatusEnum.ACTIVE)
                .orElseThrow(() -> new QrCodeNotFoundException(
                        String.format(
                                "Qr Code with ID %s was not found", qrCodeId
                        )
                ));

        Ticket ticket = qrCode.getTicket();

        return validateTicket(ticket);
    }

    private TicketValidation validateTicket(Ticket ticket) {
        //The logic, a ticket should only be scanned once, after that it becomes invalid

        TicketValidation ticketValidation = new TicketValidation();
        ticketValidation.setTicket(ticket);
        ticketValidation.setValidationMethod(TicketValidationMethodEnum.QR_SCAN);

        TicketValidationStatusEnum ticketValidationStatus = ticket.getTicketValidations().stream()
                .filter(v -> TicketValidationStatusEnum.VALID.equals(v.getStatus()))
                .findFirst()
                .map(v -> TicketValidationStatusEnum.INVALID)
                .orElse(TicketValidationStatusEnum.VALID);

        ticketValidation.setStatus(ticketValidationStatus);

        return ticketValidationRepository.save(ticketValidation);
    }


    @Override
    public TicketValidation validateTicketManually(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFoundException::new);

        return validateTicket(ticket);
    }
}

/*
Advancements
Checking if it's the correct event, add more refined definition logic
 */
