package spring.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.rest.dto.UserResponseDto;
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

  public UserResponseDto getUserById(Long id) {
    var user = userRepository.findById(id);

    if (user.isPresent()) {
      return userMapper.toUserResponseDto(user.get(), null);
    }
    return null;
  }

  @Transactional
  public UserResponseDto getAllUserStoriesById(Long id) {
    var user = userRepository.findByIdAllStories(id);

    if (user.isPresent()) {
      return userMapper.toUserResponseDto(user.get(), user.get().getStories());
    }
    return null;
  }
}
