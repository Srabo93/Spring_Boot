
package spring.rest.dto;

import java.util.List;

public record UserStoriesDto(
                                UserDto user,
                                List<StoryDto> stories) {

}
