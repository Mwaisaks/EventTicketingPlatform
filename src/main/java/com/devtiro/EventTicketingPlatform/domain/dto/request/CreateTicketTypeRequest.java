package com.devtiro.EventTicketingPlatform.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketTypeRequest {

    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;

}
