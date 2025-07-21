package com.devtiro.EventTicketingPlatform.mappers;

import com.devtiro.EventTicketingPlatform.domain.dto.response.TicketValidationResponseDto;
import com.devtiro.EventTicketingPlatform.domain.entity.TicketValidation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketValidationMapper {

    @Mapping(target = "ticketId", source = "ticket.id")
    TicketValidationResponseDto toTicketValidationResponseDto(TicketValidation ticketValidation);
}
