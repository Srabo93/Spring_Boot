package spring.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.rest.model.Story;
import spring.rest.repository.StoryRepository;

@Service
public class StoryService {

  private final StoryRepository storyRepo;

  public StoryService(StoryRepository repository) {
    this.storyRepo = repository;
  }

  public List<Story> findAllStories() {

    return storyRepo.findAll();
  }
}
