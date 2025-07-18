package com.devtiro.EventTicketingPlatform.controller;

import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateEventRequest;
import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateEventRequestDto;
import com.devtiro.EventTicketingPlatform.domain.dto.request.UpdateEventRequest;
import com.devtiro.EventTicketingPlatform.domain.dto.request.UpdateEventRequestDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.CreateEventResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.GetEventDetailsResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.ListEventResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.UpdateEventResponseDto;
import com.devtiro.EventTicketingPlatform.domain.entity.Event;
import com.devtiro.EventTicketingPlatform.mappers.EventMapper;
import com.devtiro.EventTicketingPlatform.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static com.devtiro.EventTicketingPlatform.util.JwtUtil.parseUserId;

@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto createEventRequestDto
            ){

        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userId = parseUserId(jwt);

        Event createdEvent = eventService.createEvent(userId, createEventRequest);
        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);
        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ListEventResponseDto>> getEvents(
            @AuthenticationPrincipal Jwt jwt, Pageable pageable
    ){

        UUID organizerId = parseUserId(jwt);
        Page<Event> events = eventService.listEventsForOrganizer(organizerId, pageable);
        return ResponseEntity.ok(
                events.map(eventMapper::toListEventResponseDto)
        );
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<GetEventDetailsResponseDto> getEvent(
            @AuthenticationPrincipal Jwt jwt, @PathVariable UUID eventId
    ){
        UUID userId = parseUserId(jwt);

        return eventService.getEventForOrganizer(userId, eventId)
                .map(eventMapper::toGetEventDetailsResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{eventId}")
    public ResponseEntity<UpdateEventResponseDto> updateEvent(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId,
            @Valid @RequestBody UpdateEventRequestDto updateEventRequestDto
    ){

        UpdateEventRequest updateEventRequest = eventMapper.fromDto(updateEventRequestDto);
        UUID userId = parseUserId(jwt);

        Event updatedEvent = eventService.updateEventForOrganizer(
                userId, eventId, updateEventRequest
        );

        UpdateEventResponseDto updateEventResponseDto = eventMapper.toUpdateEventResponseDto(updatedEvent);

        return new ResponseEntity<>(updateEventResponseDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{eventId}")
    public ResponseEntity<Map<String, String>> deleteEvent(
            @AuthenticationPrincipal Jwt jwt, @PathVariable UUID eventId
    ){
        UUID userId = parseUserId(jwt);

        eventService.deleteEventForOrganizer(userId, eventId);
        return ResponseEntity.ok(Map.of("message", "The event was deleted successfully"));
    }
}
