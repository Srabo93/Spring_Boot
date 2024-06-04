package spring.rest.dto;

import java.util.Date;

public record UserDto(
    Long id,
    String displayName,
    String image,
    Date createdAt) {
}
