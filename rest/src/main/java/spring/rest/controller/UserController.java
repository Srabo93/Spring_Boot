package spring.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.dto.UserDto;
import spring.rest.dto.UserStoriesDto;
import spring.rest.model.User;
import spring.rest.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> getAllUsers() {

    return userService.getAllUsers();
  }

  @GetMapping("/users/{userId}")
  public UserDto getUserById(@PathVariable Long userId) {

    return userService.getUserById(userId);
  }

  @GetMapping("/users/{userId}/stories")
  public UserStoriesDto getUserStoriesById(@PathVariable Long userId) {

    return userService.getUserStoriesByUserId(userId);
  }
}
