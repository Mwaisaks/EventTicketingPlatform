package com.devtiro.EventTicketingPlatform.service;

import com.devtiro.EventTicketingPlatform.domain.entity.Event;
import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateEventRequest;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
}
