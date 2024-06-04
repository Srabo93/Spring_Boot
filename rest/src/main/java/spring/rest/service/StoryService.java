package spring.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import spring.rest.dto.StoryDto;
import spring.rest.dto.StoryResponseDto;
import spring.rest.mapper.StoryMapper;
import spring.rest.repository.StoryRepository;

@Service
public class StoryService {

  private final StoryRepository storyRepo;
  private final StoryMapper storyMapper;

  public StoryService(StoryRepository repository, StoryMapper storyMapper) {
    this.storyRepo = repository;
    this.storyMapper = storyMapper;
  }

  public List<StoryResponseDto> findAllStories() {
    return storyRepo.findAll().stream().map(story -> storyMapper.toStoryResponse(story)).collect(Collectors.toList());
  }

  public List<StoryResponseDto> findAllPublicStories() {
    return storyRepo.findAll().stream().filter(story -> story.isPublicVisible())
        .map(story -> storyMapper.toStoryResponse(story)).collect(Collectors.toList());
  }

  public StoryResponseDto findStoryById(Long id) {
    var story = storyRepo.findById(id);

    if (story.isPresent()) {
      return storyMapper.toStoryResponse(story.get());
    }

    return null;
  }

  public StoryResponseDto createStory(@RequestBody StoryDto dto) {
    var story = storyMapper.toStory(dto);
    var savedStory = storyRepo.save(story);

    return storyMapper.toStoryResponse(savedStory);
  }

}
