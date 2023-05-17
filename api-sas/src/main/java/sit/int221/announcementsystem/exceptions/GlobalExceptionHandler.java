package sit.int221.announcementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;


import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;




@ControllerAdvice
public class GlobalExceptionHandler {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> itemNotFound(ItemNotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.parse(LocalDateTime.now().format(formatter)),
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidAnnouncementId(BadRequestException exception, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.parse(LocalDateTime.now().format(formatter)),
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseValidException> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<ErrorResponseValidException.FieldError> details =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError -> new ErrorResponseValidException.FieldError(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()))
                        .collect(Collectors.toList());
        ErrorResponseValidException apiError = new ErrorResponseValidException(
                status.value(),
                "Announcement attributes validation failed!",
                request.getDescription(false),
                details
        );
        return new ResponseEntity<>(apiError, status);
    }
}
