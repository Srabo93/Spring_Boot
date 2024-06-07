package spring.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.rest.dto.UserDto;
import spring.rest.dto.UserStoriesDto;
import spring.rest.mapper.UserMapper;
import spring.rest.model.User;
import spring.rest.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public UserDto getUserById(Long id) {
    var user = userRepository.findById(id);

    if (user.isPresent()) {
      return userMapper.toUserDto(user.get());
    }
    return null;
  }

  @Transactional
  public UserStoriesDto getUserStoriesByUserId(Long id) {

    var user = userRepository.findByIdandPublicVisibleStories(id);

    return userMapper.toUserStoriesDto(user);
  }
}
