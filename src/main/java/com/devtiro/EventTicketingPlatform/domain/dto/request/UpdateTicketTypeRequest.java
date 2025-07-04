package com.devtiro.EventTicketingPlatform.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketTypeRequest {

    private UUID id;
    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;

}
