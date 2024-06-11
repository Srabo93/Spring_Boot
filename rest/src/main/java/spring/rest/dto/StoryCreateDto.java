
package spring.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StoryCreateDto(
        @NotEmpty(message = "The Title cant be empty")
        @NotNull(message = "The Title can not be null")
        String title,
        @NotEmpty(message = "The Body cant be empty")
        @NotNull(message = "The Body can not be null")
        String body,
        boolean publicVisible,
        Long userId) implements StoryDtoI {
}
