package com.devtiro.EventTicketingPlatform.service;

import com.devtiro.EventTicketingPlatform.domain.entity.Event;
import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateEventRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);

    Page<Event> listEventsForOrganizer (UUID organizerId, Pageable pageable);

    Optional<Event> getEventForOrganizer(UUID organizerId, UUID id);
}
