package spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import spring.rest.model.User;
import spring.rest.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  // public Optional<User> getUserStoriesById(Long id) {
  //
  // // return userRepository.findAll();
  // }
}
