package edu.aust.order;

import edu.aust.enums.ErrorResponseCode;
import edu.aust.order.dto.ErrorResponse;
import edu.aust.order.exceptions.OrderNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleOrderNotFound
            (OrderNotFoundException nfe) {

        ErrorResponse errorResponse =
                new ErrorResponse(ErrorResponseCode.ORDER_NOT_FOUND,
                        nfe.getMessage());

        return new ResponseEntity<ErrorResponse>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException
            (DataIntegrityViolationException ex) {

        ErrorResponse errorResponse =
                new ErrorResponse(ErrorResponseCode.BAD_INPUT,
                        "Duplicate key value violates unique constraint.");

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }

}