package spring.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotfoundException(ResourceNotFoundException e, WebRequest request) {

    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<?> handleBadRequestException(BadRequestException e, WebRequest request) {

    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceExistsException.class)
  public ResponseEntity<?> handleBadRequestException(ResourceExistsException e, WebRequest request) {

    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.IM_USED);

    return new ResponseEntity<>(errorResponse, HttpStatus.IM_USED);
  }

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<?> handleBadRequestException(InternalServerErrorException e, WebRequest request) {

    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
