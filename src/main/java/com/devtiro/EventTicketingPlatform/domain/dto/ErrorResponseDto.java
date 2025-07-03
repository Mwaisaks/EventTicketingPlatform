package com.devtiro.EventTicketingPlatform.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String errorCode;
    private Map<String, Object> details;

}
