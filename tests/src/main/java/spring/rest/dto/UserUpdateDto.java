package spring.rest.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDto(
        @NotNull(message = "The User id is null") 
        Long id,
        String displayName,
        String image,
        Date createdAt) implements UserDtoI {
}
