package spring.rest.dto;

import jakarta.validation.constraints.NotNull;

public record StoryUpdateDto(
        @NotNull(message = "The Story id is null")
        Long id,
        String title,
        String body,
        boolean publicVisible,
        Long userId) implements StoryDtoI {
}
