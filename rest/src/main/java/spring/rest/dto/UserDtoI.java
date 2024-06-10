
package spring.rest.dto;

import java.util.Date;

public interface UserDtoI {
  Long id();

  String displayName();

  String image();

  Date createdAt();
}
