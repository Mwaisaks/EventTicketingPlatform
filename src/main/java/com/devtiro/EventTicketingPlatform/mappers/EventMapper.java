package com.devtiro.EventTicketingPlatform.mappers;

import com.devtiro.EventTicketingPlatform.domain.dto.request.*;
import com.devtiro.EventTicketingPlatform.domain.dto.response.*;
import com.devtiro.EventTicketingPlatform.domain.entity.Event;
import com.devtiro.EventTicketingPlatform.domain.entity.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

    ListEventResponseDto toListEventResponseDto (Event event);

    Event fromListEventResponseDto (ListEventResponseDto listEventResponseDto);

    GetEventDetailsTicketTypesResponseDto toGetEventDetailsTicketTypesResponseDto (TicketType ticketType);

    GetEventDetailsResponseDto toGetEventDetailsResponseDto (Event event);

    UpdateEventRequest fromDto (UpdateEventRequestDto dto);

    UpdateTicketTypeRequest fromDto (UpdateTicketTypeRequestDto dto);

    UpdateEventResponseDto toUpdateEventResponseDto (Event event);

    UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto (TicketType ticketType);

    ListPublishedEventsResponseDto toListPublishedEventsResponseDto(Event event);

    GetPublishedEventDetailsResponseDto toGetPublishedEventDetailsResponseDto (Event event);

    GetPublishedEventDetailsTicketTypesResponseDto toGetPublishedEventDetailsTicketTypesResponseDto (TicketType ticketType);
}
