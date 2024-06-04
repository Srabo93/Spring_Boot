package spring.rest.mapper;

import org.springframework.stereotype.Service;

import spring.rest.dto.StoryDto;
import spring.rest.dto.StoryResponseDto;
import spring.rest.dto.UserDto;
import spring.rest.model.Story;
import spring.rest.model.User;

@Service
public class StoryMapper {

  public StoryResponseDto toStoryResponse(Story story) {

    return new StoryResponseDto(
        story.getTitle(),
        story.getBody(),
        story.isPublicVisible(),
        story.getCreatedAt(),
        (new UserDto(story.getUser().getId(), story.getUser().getDisplayName(), story.getUser().getImage(),
            story.getUser().getCreatedAt())));
  }

  public Story toStory(StoryDto dto) {
    var user = new User();
    user.setId(dto.userId());

    var story = new Story();
    story.setTitle(dto.title());
    story.setBody(dto.body());
    story.setPublicVisible(dto.publicVisible());
    story.setCreatedAt(dto.createdAt());
    story.setUser(user);

    return story;
  }

}
