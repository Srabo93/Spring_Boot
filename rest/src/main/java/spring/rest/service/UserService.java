package spring.rest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.rest.dto.UserCreateDto;
import spring.rest.dto.UserResponseDto;
import spring.rest.dto.UserUpdateDto;
import spring.rest.mapper.UserMapper;
import spring.rest.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public List<UserResponseDto> getAllUsers() {
    var users = userRepository.findAll();

    return users.stream().map(user -> userMapper.toUserResponseDto(user, null)).collect(Collectors.toList());
  }

  public UserResponseDto createUser(UserCreateDto dto) {
    var user = userMapper.toUser(dto);

    userRepository.save(user);

    return userMapper.toUserResponseDto(user, null);
  }

  public UserResponseDto getUserById(Long id) {
    var user = userRepository.findById(id);

    if (user.isPresent()) {
      return userMapper.toUserResponseDto(user.get(), null);
    }
    return null;
  }

  public UserResponseDto replaceUserById(UserUpdateDto dto) {

    var findUser = userRepository.findById(dto.id());

    if (findUser.isPresent()) {
      var user = findUser.get();

      user.setId(dto.id());
      user.setCreatedAt(dto.createdAt());
      user.setImage(dto.image());
      user.setDisplayName(dto.displayName());

      return userMapper.toUserResponseDto(user, null);
    }

    return null;
  }

  @Transactional
  public UserResponseDto getAllUserStoriesById(Long id, Optional<Boolean> publicVisible) {
    var user = userRepository.findByIdAllStories(id, publicVisible);
    System.out.println(publicVisible);

    if (user.isPresent()) {
      return userMapper.toUserResponseDto(user.get(), user.get().getStories());
    }
    return null;
  }

  public Map<String, Object> deleteUserById(Long id) {

    userRepository.deleteById(id);

    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "User deleted successfully");
    response.put("id", id);

    return response;
  }
}
