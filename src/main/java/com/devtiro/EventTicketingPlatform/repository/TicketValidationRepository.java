package com.devtiro.EventTicketingPlatform.repository;

import com.devtiro.EventTicketingPlatform.domain.entity.TicketValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketValidationRepository extends JpaRepository<TicketValidation, UUID> {


}
