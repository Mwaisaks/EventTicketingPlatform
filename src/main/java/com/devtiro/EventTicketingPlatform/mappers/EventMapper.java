package com.devtiro.EventTicketingPlatform.mappers;

import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateEventRequest;
import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateEventRequestDto;
import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateTicketTypeRequest;
import com.devtiro.EventTicketingPlatform.domain.dto.request.CreateTicketTypeRequestDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.CreateEventResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.GetEventDetailsResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.GetEventDetailsTicketTypesResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.ListEventResponseDto;
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
}
