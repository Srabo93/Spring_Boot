package spring.rest.dto;

import java.sql.Date;

public record UserCreateDto(
        Long id,
        Date createdAt,
        String displayName,
        String image) implements UserDtoI {
};
