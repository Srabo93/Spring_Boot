package spring.rest.dto;

import java.util.Date;

public record UserUpdateDto(
    Long id,
    String displayName,
    String image,
    Date createdAt) implements UserDtoI {
}
