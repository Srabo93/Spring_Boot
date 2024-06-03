package spring.rest;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import spring.rest.model.Story;
import spring.rest.model.User;
import spring.rest.repository.StoryRepository;
import spring.rest.repository.UserRepository;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
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
