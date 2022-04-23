package io.swagger.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    // This is a convenience class that formats error messages as an object rather than
    // raw string
    private class ErrorResponse
    {
        private String errorMessage;
    }

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity handle(NotFoundException exception)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler({ ApiException.class })
    public ResponseEntity handle(ApiException exception)
    {
        return ResponseEntity.status(exception.getCode()).body(new ErrorResponse(exception.getMessage()));
    }
}
