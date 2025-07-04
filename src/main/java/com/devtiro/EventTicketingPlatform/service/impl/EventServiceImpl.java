package com.devtiro.EventTicketingPlatform.service.impl;

import com.devtiro.EventTicketingPlatform.domain.entity.Event;
import com.devtiro.EventTicketingPlatform.domain.entity.TicketType;
import com.devtiro.EventTicketingPlatform.domain.entity.User;
import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateEventRequest;
import com.devtiro.EventTicketingPlatform.repository.EventRepository;
import com.devtiro.EventTicketingPlatform.repository.UserRepository;
import com.devtiro.EventTicketingPlatform.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {

        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with ID '%s' not found", organizerId))
                );

        List<TicketType> ticketTypesToCreate = event.getTicketType().stream().map(
                ticketType ->
                {
                    TicketType ticketTypeToCreate = new TicketType();

                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    return ticketTypeToCreate;
                }
        ).toList();

        Event eventToCreate = new Event();
        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }

    @Override
    public Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable) {
        return eventRepository.findByOrganizerId(organizerId, pageable);
    }

}
