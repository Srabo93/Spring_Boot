
package spring.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public record UserResponseDto(
        UserDto user,
        @JsonInclude(JsonInclude.Include.NON_NULL) 
        List<StoryResponseDto> stories) {
}
