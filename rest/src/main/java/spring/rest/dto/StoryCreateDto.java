
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
        @NotNull(message = "Public Visibility cant be null")
        boolean publicVisible,
        @NotNull(message = "The user id cant be null")
        Long userId
) implements StoryDtoI {
}
