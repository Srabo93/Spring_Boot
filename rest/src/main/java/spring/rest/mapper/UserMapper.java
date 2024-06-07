package spring.rest.mapper;

import org.springframework.stereotype.Service;

import spring.rest.dto.UserDto;
import spring.rest.dto.UserStoriesDto;
import spring.rest.model.User;

@Service
public class UserMapper {
  private final StoryMapper storyMapper;

  public UserMapper(StoryMapper storyMapper) {
    this.storyMapper = storyMapper;
  }

  public UserDto toUserDto(User user) {
    return new UserDto(user.getId(), user.getDisplayName(), user.getImage(), user.getCreatedAt());
  }

  public UserStoriesDto toUserStoriesDto(User user) {
    var userDto = toUserDto(user);
    var storyDtos = storyMapper.toStoryList(user.getStories());
    return new UserStoriesDto(userDto, storyDtos);
  }

  public User toUser(UserDto dto) {
    var user = new User();
    // user.setId(dto.userId());
    //
    // var story = new Story();
    // story.setTitle(dto.title());
    // story.setBody(dto.body());
    // story.setPublicVisible(dto.publicVisible());
    // story.setCreatedAt(dto.createdAt());
    // story.setUser(user);

    return user;
  }

}
