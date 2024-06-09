
package spring.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

public record StoryResponseDto(
        Long id,
        String title,
        String body,
        boolean publicVisible,
        Date createdAt,
        @JsonInclude(JsonInclude.Include.NON_NULL) UserDto user) {
}
