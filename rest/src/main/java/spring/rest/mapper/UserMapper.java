package spring.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import spring.rest.dto.UserCreateDto;
import spring.rest.dto.UserDto;
import spring.rest.dto.UserResponseDto;
import spring.rest.model.Story;
import spring.rest.model.User;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDto userToUserDto(User user);

  UserResponseDto userToUserResponseDto(UserDto user, List<Story> stories);

  @Mapping(target = "stories", ignore = true)
  User userCreatedDtoToUser(UserCreateDto dto);
}
