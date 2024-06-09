package spring.rest.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.rest.dto.UserDto;
import spring.rest.dto.UserResponseDto;
import spring.rest.model.Story;
import spring.rest.model.User;

@Service
public class UserMapper {
  private final StoryMapper storyMapper;

  public UserMapper(StoryMapper storyMapper) {
    this.storyMapper = storyMapper;
  }

  public UserDto toUserDto(User user) {
    if (user == null) {
      return null;
    }
    return new UserDto(user.getId(), user.getDisplayName(), user.getImage(), user.getCreatedAt());
  }

  public UserResponseDto toUserResponseDto(User user, List<Story> stories) {

    if (user == null) {
      return null;
    }

    var userDto = toUserDto(user);
    var storyDtos = storyMapper.toStoryDtoList(stories);
    return new UserResponseDto(userDto, storyDtos);
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
