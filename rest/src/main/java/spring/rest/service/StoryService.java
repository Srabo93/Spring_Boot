package spring.rest.service;

import java.util.List;
import java.util.Optional;

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

  public Optional<Story> findStoryById(Long id) {
    return storyRepo.findById(id);
  }

}
