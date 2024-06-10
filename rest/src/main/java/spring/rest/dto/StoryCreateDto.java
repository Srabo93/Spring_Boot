
package spring.rest.dto;

public record StoryCreateDto(
        String title,
        String body,
        boolean publicVisible,
        Long userId) implements StoryDtoI {
}
