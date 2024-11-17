package com.alluz.blog.web.exp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handles Resource Not Found Exception with a 404 status.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        LOGGER.error("Resource not found: {}", ex.getMessage());
        return createProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * Handles Authentication Failures with a 401 status.
     */
    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException ex) {
        LOGGER.error("Authentication failure: {}", ex.getMessage());
        return createProblemDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    /**
     * Handles Access Denied Exception with a 403 status.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex) {
        LOGGER.error("Access denied: {}", ex.getMessage());
        return createProblemDetail(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    /**
     * Handles unexpected exceptions with a 500 status.
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        LOGGER.error("Unexpected error: {}", ex.getMessage(), ex);
        return createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred. Please try again later.");
    }

    /**
     * Helper method to create a ProblemDetail response with the specified status and detail.
     */
    private ProblemDetail createProblemDetail(HttpStatus status, String detail) {
        return ProblemDetail.forStatusAndDetail(status, detail);
    }
}
