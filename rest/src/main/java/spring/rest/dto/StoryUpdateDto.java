package spring.rest.dto;

public record StoryUpdateDto(
        Long id,
        String title,
        String body,
        boolean publicVisible,
        Long userId) implements StoryDtoI {
}
