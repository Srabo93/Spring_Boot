package spring.rest.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
  public String errorMessage;
  public HttpStatus httpStatus;

  public ErrorResponse(String errorMessage, HttpStatus httpStatus) {
    this.errorMessage = errorMessage;
    this.httpStatus = httpStatus;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

}
