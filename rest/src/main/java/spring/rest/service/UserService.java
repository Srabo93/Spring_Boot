package spring.rest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import spring.rest.dto.UserCreateDto;
import spring.rest.dto.UserResponseDto;
import spring.rest.dto.UserUpdateDto;
import spring.rest.exceptions.InternalServerErrorException;
import spring.rest.exceptions.ResourceExistsException;
import spring.rest.exceptions.ResourceNotFoundException;
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

    if (users == null) {
      throw new InternalServerErrorException("No Users Found");
    }

    return users.stream().map(user -> userMapper.userToUserResponseDto(userMapper.userToUserDto(user), null))
        .collect(Collectors.toList());
  }

  public UserResponseDto createUser(@Valid UserCreateDto dto) {
    var user = userMapper.userCreatedDtoToUser(dto);

    userRepository.findByDisplayName(user.getDisplayName()).ifPresent(existingUser -> {
      throw new ResourceExistsException(existingUser.getDisplayName() + " User is already used");
    });

    userRepository.save(user);

    return userMapper.userToUserResponseDto(userMapper.userToUserDto(user), null);
  }

  public UserResponseDto getUserById(Long id) {
    var user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

    return userMapper.userToUserResponseDto(userMapper.userToUserDto(user), null);
  }

  public UserResponseDto replaceUserById(@Valid UserUpdateDto dto) {

    var findUser = userRepository.findById(dto.id());

    if (findUser.isPresent()) {
      var user = findUser.get();
      user.setId(dto.id());
      user.setCreatedAt(dto.createdAt());
      user.setImage(dto.image());
      user.setDisplayName(dto.displayName());

      return userMapper.userToUserResponseDto(userMapper.userToUserDto(user), null);
    }

    throw new ResourceNotFoundException("No User Found");
  }

  @Transactional
  public UserResponseDto getAllUserStoriesById(Long id, Optional<Boolean> publicVisible) {
    var user = userRepository.findByIdAllStories(id, publicVisible);

    if (user.isPresent()) {
      return userMapper.userToUserResponseDto(userMapper.userToUserDto(user.get()), user.get().getStories());
    }
    return null;
  }

  public ResponseEntity<?> deleteUserById(Long id) {

    userRepository.deleteById(id);

    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "User deleted successfully");
    response.put("id", id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
