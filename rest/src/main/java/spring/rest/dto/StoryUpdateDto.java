package spring.rest.dto;

public record StoryUpdateDto(
    String title,
    String body,
    boolean publicVisible,
    Long userId) {
}
