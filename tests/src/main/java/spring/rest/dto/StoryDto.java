package spring.rest.dto;

import java.util.Date;

public record StoryDto(
        Long id,
        String title,
        String body,
        boolean publicVisible,
        Date createdAt,
        Long userId) implements StoryDtoI {
}
