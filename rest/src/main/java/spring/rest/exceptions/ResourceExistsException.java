package spring.rest.exceptions;

public class ResourceExistsException extends RuntimeException {
  public ResourceExistsException(String message) {
    super(message);
  }
}
