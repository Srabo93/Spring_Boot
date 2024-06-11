package spring.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StoryUpdateDto(
        @NotNull(message = "The Story id is null") 
        @NotEmpty(message = "The Story id is empty") 
        Long id,
        String title,
        String body,
        boolean publicVisible,
        Long userId) implements StoryDtoI {
}
