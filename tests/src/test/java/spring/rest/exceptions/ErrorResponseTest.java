package spring.rest.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ErrorResponseTest {

  private ErrorResponse errorResponse;

  @BeforeEach
  public void setUp() {
    errorResponse = new ErrorResponse("An error occured", HttpStatus.BAD_REQUEST);
  }

  @Test
  void testConstructor() {
    assertEquals("An error occured", errorResponse.getErrorMessage());
    assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getHttpStatus());
  }

  @Test
  void testGetErrorMessage() {
    assertEquals("An error occured", errorResponse.getErrorMessage());
  }

  @Test
  void testSetErrorMessage() {
    errorResponse.setErrorMessage("New Error Message");
    assertEquals("New Error Message", errorResponse.getErrorMessage());
  }

  @Test
  void testGetHttpStatus() {
    assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getHttpStatus());
  }

  @Test
  void testSetHttpStatus() {
    errorResponse.setHttpStatus(HttpStatus.OK);
    assertEquals(HttpStatus.OK, errorResponse.getHttpStatus());
  }
}
