package com.devtiro.EventTicketingPlatform.controller;

import com.devtiro.EventTicketingPlatform.domain.dto.ErrorResponseDto;
import com.devtiro.EventTicketingPlatform.exceptions.EventNotFoundException;
import com.devtiro.EventTicketingPlatform.exceptions.EventUpdateException;
import com.devtiro.EventTicketingPlatform.exceptions.TicketTypeNotFoundException;
import com.devtiro.EventTicketingPlatform.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Error code constants
    private static final String VALIDATION_ERROR = "VALIDATION_ERROR";
    private static final String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION";
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String EVENT_NOT_FOUND = "EVENT_NOT_FOUND";
    private static final String TICKET_TYPE_NOT_FOUND = "TICKET_TYPE_NOT_FOUND";
    private static final String EVENT_UPDATE_FAILED = "EVENT_UPDATE_FAILED";
    private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    // Generic Exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(
            Exception ex, WebRequest request) {

        log.error("Caught Exception", ex);

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("An unexpected error occurred")
                .errorCode(INTERNAL_SERVER_ERROR)
                .path(extractPath(request))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {

        log.error("Caught ConstraintViolationException", ex);

        Map<String, String> violations = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                violations.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Input validation constraints violated")
                .errorCode(CONSTRAINT_VIOLATION)
                .path(extractPath(request))
                .details(Map.of("violations", violations))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        fieldErrors.put(error.getField(), error.getDefaultMessage())
                );

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Invalid input data")
                .errorCode(VALIDATION_ERROR)
                .path(extractPath(request))
                .details(Map.of("fieldErrors", fieldErrors))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {

        log.error("User not found: {}", ex.getMessage());

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("User Not Found")
                .message(ex.getMessage())
                .errorCode(USER_NOT_FOUND)
                .path(extractPath(request))
                .details(Map.of(
                        "suggestedAction", "Register for a new account",
                        "registrationEndpoint", "/auth/register"
                ))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEventNotFoundException(
            EventNotFoundException ex, WebRequest request) {

        log.error("Event not found: {}", ex.getMessage());

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Event Not Found")
                .message(ex.getMessage())
                .errorCode(EVENT_NOT_FOUND)
                .path(extractPath(request))
                .details(Map.of(
                        "suggestedAction", "Browse available events",
                        "helpfulEndpoints", List.of("/events", "/events/search")
                ))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketTypeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTicketTypeNotFoundException(
            TicketTypeNotFoundException ex, WebRequest request) {

        log.error("Caught TicketTypeNotFoundException", ex);

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Ticket Type Not Found")
                .message(ex.getMessage())
                .errorCode(TICKET_TYPE_NOT_FOUND)
                .path(extractPath(request))
                .details(Map.of(
                        "suggestedAction", "View available ticket types for this event",
                        "availableEndpoint", "/events/{eventId}/ticket-types"
                ))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleEventUpdateException(
            EventUpdateException ex, WebRequest request) {

        log.error("Caught EventUpdateException", ex);

        // Determine appropriate status based on exception details
        HttpStatus status;
        String errorCode;

        if (ex.getCause() instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            errorCode = "INVALID_EVENT_UPDATE";
        } else if (ex.getMessage().toLowerCase().contains("business rule") ||
                ex.getMessage().toLowerCase().contains("conflict")) {
            status = HttpStatus.CONFLICT;
            errorCode = "EVENT_UPDATE_CONFLICT";
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            errorCode = EVENT_UPDATE_FAILED;
        }

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(status.value())
                .error("Unable to update event")
                .message(ex.getMessage())
                .errorCode(errorCode)
                .path(extractPath(request))
                .details(Map.of(
                        "suggestedAction", "Review event details and ensure all required fields are valid",
                        "commonIssues", List.of("Event date in the past", "Invalid venue", "Ticket capacity exceeded")
                ))
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }

    private String extractPath(WebRequest request) {
        String description = request.getDescription(false);
        return description.startsWith("uri=") ? description.substring(4) : description;
    }
}