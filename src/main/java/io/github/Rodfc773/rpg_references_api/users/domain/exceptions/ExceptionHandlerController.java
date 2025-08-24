package io.github.Rodfc773.rpg_references_api.users.domain.exceptions;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource){ this.messageSource = messageSource;}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ErrorMessageDTO> dto = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            ErrorMessageDTO e = new ErrorMessageDTO(message, fieldError.getField());

            dto.add(e);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
