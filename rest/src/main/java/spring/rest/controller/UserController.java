package spring.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.dto.UserResponseDto;
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

  @GetMapping("/{userId}")
  public UserResponseDto getUserById(@PathVariable Long userId) {

    return userService.getUserById(userId);
  }

  @GetMapping("/{userId}/stories")
  public UserResponseDto getAllUserStories(@PathVariable Long userId) {

    return userService.getAllUserStoriesById(userId);
  }
}
