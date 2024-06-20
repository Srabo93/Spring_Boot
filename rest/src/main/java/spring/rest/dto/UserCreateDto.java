package spring.rest.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserCreateDto(
        Long id,
        Date createdAt,
        @NotNull(message = "Name can not be null")
        @NotEmpty(message = "Name can not be empty")
        String displayName,
        String image) implements UserDtoI {
};
