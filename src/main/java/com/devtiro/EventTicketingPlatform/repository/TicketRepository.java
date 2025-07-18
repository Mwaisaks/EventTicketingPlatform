package com.devtiro.EventTicketingPlatform.repository;

import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    int countByTicketTypeId(UUID ticketTypeId);

    Page<Ticket> findByPurchaserId(UUID purchaserId, Pageable pageable);

    //Ticket findByPurchaserId(UUID purchaserId);

    Optional<Ticket> findByIdAndPurchaserId(UUID ticketId, UUID purchaserId);
}
