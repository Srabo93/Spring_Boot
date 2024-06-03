package spring.rest.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import spring.rest.model.Story;
import spring.rest.model.User;
import spring.rest.repository.StoryRepository;
import spring.rest.repository.UserRepository;

@Configuration
public class AppConfig {

  @Bean
  @Profile("dev")
  CommandLineRunner runner(StoryRepository repo, UserRepository userRepo) {
    return args -> {
      User user = new User();
      user.setImage("fakeimg.png");
      user.setDisplayName("username");
      userRepo.save(user);

      Story story = new Story();
      story.setTitle("first");
      story.setBody("This is the body text");
      story.setPublicVisible(false);
      story.setUser(user);
      repo.save(story);

    };
  }
}
