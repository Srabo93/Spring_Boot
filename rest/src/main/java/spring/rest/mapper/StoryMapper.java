package spring.rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import spring.rest.dto.StoryDtoI;
import spring.rest.dto.StoryResponseDto;
import spring.rest.dto.UserDto;
import spring.rest.model.Story;
import spring.rest.model.User;

@Service
public class StoryMapper {

  public StoryResponseDto toStoryResponseDto(Story story) {

    return new StoryResponseDto(
        story.getId(),
        story.getTitle(),
        story.getBody(),
        story.isPublicVisible(),
        story.getCreatedAt(),
        (new UserDto(story.getUser().getId(), story.getUser().getDisplayName(), story.getUser().getImage(),
            story.getUser().getCreatedAt())));
  }

  public List<StoryResponseDto> toStoryDtoList(List<Story> stories) {

    if (stories == null) {
      return null;
    }
    return stories.stream()
        .map(story -> new StoryResponseDto(
            story.getId(),
            story.getTitle(),
            story.getBody(),
            story.isPublicVisible(),
            story.getCreatedAt(),
            new UserDto(story.getUser().getId(),
                story.getUser().getDisplayName(),
                story.getUser().getImage(),
                story.getUser().getCreatedAt())))
        .collect(Collectors.toList());

  }

  public Story toStory(StoryDtoI dto) {

    var story = new Story();
    story.setTitle(dto.title());
    story.setBody(dto.body());
    story.setPublicVisible(dto.publicVisible());

    if (dto.userId() != null) {
      var user = new User();
      user.setId(dto.userId());
      story.setUser(user);
    }

    return story;
  }

}
