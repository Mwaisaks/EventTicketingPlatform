package com.devtiro.EventTicketingPlatform.domain.dto.response;

import com.devtiro.EventTicketingPlatform.domain.entity.TicketType;
import com.devtiro.EventTicketingPlatform.domain.enums.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketResponseDto {

    private UUID id;
    private TicketStatusEnum status;
    private Double price;
    private String description;
    private String eventName;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private String eventVenue;

}
