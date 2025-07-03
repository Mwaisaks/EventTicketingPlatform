package com.devtiro.EventTicketingPlatform.exceptions;

import com.devtiro.EventTicketingPlatform.domain.dto.ErrorResponseDto;

public class EventTicketException extends RuntimeException{

    public EventTicketException() {
    }

    public EventTicketException(String message) {
        super(message);
    }

    public EventTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventTicketException(Throwable cause) {
        super(cause);
    }

    public EventTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorCode() {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        return errorResponseDto.getErrorCode();
    }
}
