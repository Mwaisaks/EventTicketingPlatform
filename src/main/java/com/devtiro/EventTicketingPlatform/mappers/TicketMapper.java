package com.devtiro.EventTicketingPlatform.mappers;

import com.devtiro.EventTicketingPlatform.domain.dto.response.GetTicketResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.ListTicketResponseDto;
import com.devtiro.EventTicketingPlatform.domain.dto.response.ListTicketsTicketTypeResponseDto;
import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;
import com.devtiro.EventTicketingPlatform.domain.entity.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    ListTicketsTicketTypeResponseDto toListTicketsTicketTypeResponseDto(TicketType ticketType);

    ListTicketResponseDto toListTicketResponseDto (Ticket ticket);

    @Mapping(target = "price", source = "ticket.ticketType.price")
    @Mapping(target = "description", source = "ticket.ticketType.description")
    @Mapping(target = "eventName", source = "ticket.ticketType.event.name")
    @Mapping(target = "eventVenue", source = "ticket.ticketType.event.venue")
    @Mapping(target = "eventStart", source = "ticket.ticketType.event.start")
    @Mapping(target = "eventEnd", source = "ticket.ticketType.event.end")
    GetTicketResponseDto toGetTicketResponseDto(Ticket ticket);
}
