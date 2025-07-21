package com.devtiro.EventTicketingPlatform.repository;

import com.devtiro.EventTicketingPlatform.domain.entity.QrCode;
import com.devtiro.EventTicketingPlatform.domain.enums.QrCodeStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {

    Optional<QrCode> findByTicketIdAndTicketPurchaserId(UUID ticketId, UUID ticketPurchaserId);

    Optional<QrCode> findByIdAndStatus(UUID qrCodeId, QrCodeStatusEnum status);

}
