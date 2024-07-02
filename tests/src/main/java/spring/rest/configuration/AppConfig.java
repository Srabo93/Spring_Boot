package spring.rest.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.github.javafaker.Faker;

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
      for (int i = 0; i < 10; i++) {

        Faker faker = new Faker();

        User user = new User();
        user.setImage(faker.avatar().image());
        user.setDisplayName(faker.name().username());
        userRepo.save(user);

        for (int j = 0; j < 4; j++) {
          Story story = new Story();
          story.setTitle(faker.book().title());
          story.setBody(faker.lorem().paragraph(7));
          story.setPublicVisible(faker.random().nextBoolean());
          story.setUser(user);
          repo.save(story);
        }

      }
    };
  }
}
