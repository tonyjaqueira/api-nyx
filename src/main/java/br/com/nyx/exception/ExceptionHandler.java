package br.com.nyx.exception;

import br.com.nyx.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExceptionCustomer.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCustomerLoginException(ExceptionCustomer ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()) {
        };
    }

}
