package sit.int221.announcementsystem.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerValid {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseValidException> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorResponseValidException apiError = new ErrorResponseValidException();
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());
        apiError.setTitle("Announcement attributes validation failed!");
        apiError.setInstance(request.getDescription(false));
        apiError.setDetail(ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorResponseValidException.FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList()));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}