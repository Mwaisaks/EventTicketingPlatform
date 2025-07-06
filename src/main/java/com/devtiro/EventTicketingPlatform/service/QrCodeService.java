package com.devtiro.EventTicketingPlatform.service;

import com.devtiro.EventTicketingPlatform.domain.entity.QrCode;
import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;

public interface QrCodeService {

    QrCode generateQrCode(Ticket ticket);

}
