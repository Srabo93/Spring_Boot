package spring.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.rest.dto.UserCreateDto;
import spring.rest.dto.UserResponseDto;
import spring.rest.dto.UserUpdateDto;
import spring.rest.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping()
  public List<UserResponseDto> getAllUsers() {

    return userService.getAllUsers();
  }

  @PostMapping()
  public UserResponseDto createUser(@Valid @RequestBody UserCreateDto dto) {

    return userService.createUser(dto);
  }

  @GetMapping("/{userId}")
  public UserResponseDto getUserById(@PathVariable Long userId) {

    return userService.getUserById(userId);
  }

  @PutMapping("/{userId}")
  public UserResponseDto replaceUserById(@Valid @RequestBody UserUpdateDto dto) {
    return userService.replaceUserById(dto);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
    return userService.deleteUserById(userId);
  }

  @GetMapping("/{userId}/stories")
  public UserResponseDto getAllUserStories(@PathVariable Long userId,
      @Valid @RequestParam Optional<Boolean> publicVisible) {

    return userService.getAllUserStoriesById(userId, publicVisible);
  }
}
