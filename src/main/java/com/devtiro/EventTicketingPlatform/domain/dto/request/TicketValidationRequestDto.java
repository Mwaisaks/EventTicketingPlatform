package com.devtiro.EventTicketingPlatform.domain.dto.request;

import com.devtiro.EventTicketingPlatform.domain.enums.TicketValidationMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketValidationRequestDto {
    private UUID id;
    private TicketValidationMethodEnum method;
}
