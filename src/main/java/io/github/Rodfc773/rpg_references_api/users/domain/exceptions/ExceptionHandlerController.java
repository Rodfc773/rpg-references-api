package io.github.Rodfc773.rpg_references_api.users.domain.exceptions;


import io.github.Rodfc773.rpg_references_api.common.domain.exceptions.ResourceNotFound;
import jakarta.persistence.ElementCollection;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource){ this.messageSource = messageSource;}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception, WebRequest request
            )
    {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            ErrorMessageDTO e = new ErrorMessageDTO(message, fieldError.getField());

            e.setTimeStamp();
            e.setPath(request.getDescription(false).replace("uri=", ""));
            e.setStatus(HttpStatus.BAD_REQUEST.value());

            dto.add(e);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorMessageDTO> handleInvalidDataException(InvalidDataException exception, WebRequest request){

        ErrorMessageDTO dto = new ErrorMessageDTO(exception.getMessage(), "");

        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setPath(request.getDescription(false).replace("uri=", ""));
        dto.setTimeStamp();

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public  ResponseEntity<ErrorMessageDTO> handleUserAlreadyExistException(
            UserAlreadyExists exception, WebRequest request
    ){
        ErrorMessageDTO dto = new ErrorMessageDTO(exception.getMessage(), "");

        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        dto.setPath(request.getDescription(false).replace("uri=", ""));
        dto.setTimeStamp();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorMessageDTO> handleResourceNotFoundException(
            ResourceNotFound exception, WebRequest request

    ){
        ErrorMessageDTO dto = new ErrorMessageDTO(exception.getMessage(), "");

        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setPath(request.getDescription(false).replace("uri=", ""));
        dto.setTimeStamp();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> handleException(Exception exception, WebRequest request){

        ErrorMessageDTO dto = new ErrorMessageDTO(exception.getMessage(), "");

        dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dto.setPath(request.getDescription(false).replace("uri=", ""));
        dto.setTimeStamp();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);

    }

}
