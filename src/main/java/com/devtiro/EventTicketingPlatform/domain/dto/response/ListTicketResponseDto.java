package com.devtiro.EventTicketingPlatform.domain.dto.response;

import com.devtiro.EventTicketingPlatform.domain.entity.TicketType;
import com.devtiro.EventTicketingPlatform.domain.enums.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListTicketResponseDto {

    private UUID id;
    private TicketStatusEnum status;
    private List<ListTicketsTicketTypeResponseDto> ticketTypes = new ArrayList<>();

}
