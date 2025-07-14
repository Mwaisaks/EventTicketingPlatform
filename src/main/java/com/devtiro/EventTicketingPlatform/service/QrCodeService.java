package com.devtiro.EventTicketingPlatform.service;

import com.devtiro.EventTicketingPlatform.domain.entity.QrCode;
import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;

import java.util.Optional;
import java.util.UUID;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);

    byte[] getQrCodeImageForUserAndTicket (UUID userId, UUID ticketId);
}
