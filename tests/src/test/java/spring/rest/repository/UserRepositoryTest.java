package spring.rest.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.rest.model.User;
import spring.rest.model.Story;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  private User user;

  @BeforeEach
  void setUp() {
    user = User.builder()
        .displayName("testUser")
        .build();

    Story publicStory1 = Story.builder()
        .title("Public Story 1")
        .body("Public content 1")
        .publicVisible(true)
        .user(user)
        .build();

    Story publicStory2 = Story.builder()
        .title("Public Story 2")
        .body("Public content 2")
        .publicVisible(true)
        .user(user)
        .build();

    Story privateStory = Story.builder()
        .title("Private Story")
        .body("Private content")
        .publicVisible(false)
        .user(user)
        .build();

    user.setStories(Arrays.asList(publicStory1, publicStory2, privateStory));

    userRepository.save(user);
  }

  @Test
  void findByIdAllStories_WhenPublicVisibleIsNull_ReturnsAllStories() {
    Optional<User> result = userRepository.findByIdAllStories(user.getId(), Optional.empty());

    assertTrue(result.isPresent());
    User foundUser = result.get();
    assertEquals(user.getId(), foundUser.getId());
    assertEquals(3, foundUser.getStories().size());
  }

  @Test
  void findByIdAllStories_WhenUserDoesNotExist_ReturnsEmptyOptional() {
    Optional<User> result = userRepository.findByIdAllStories(999L, Optional.empty());

    assertTrue(result.isEmpty());
  }
}
