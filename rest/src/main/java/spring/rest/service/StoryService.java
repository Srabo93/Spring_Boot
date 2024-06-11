package spring.rest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import spring.rest.dto.StoryCreateDto;
import spring.rest.dto.StoryResponseDto;
import spring.rest.dto.StoryUpdateDto;
import spring.rest.dto.UserDto;
import spring.rest.exceptions.ResourceNotFoundException;
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
    return storyRepo.findAll().stream().map(story -> storyMapper.toStoryResponseDto(story))
        .collect(Collectors.toList());
  }

  public List<StoryResponseDto> findAllPublicStories() {
    return storyRepo.findAll().stream().filter(story -> story.isPublicVisible())
        .map(story -> storyMapper.toStoryResponseDto(story)).collect(Collectors.toList());
  }

  public StoryResponseDto findStoryById(Long id) {
    var story = storyRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Story not found with id: " + id));

    return storyMapper.toStoryResponseDto(story);
  }

  public StoryResponseDto createStory(@Valid @RequestBody StoryCreateDto dto) {
    var story = storyMapper.toStory(dto);

    var savedStory = storyRepo.save(story);

    return storyMapper.toStoryResponseDto(savedStory);
  }

  public StoryResponseDto replaceStoryById(StoryUpdateDto dto) {

    var findStoryById = storyRepo.findById(dto.id());

    if (findStoryById.isEmpty()) {
      throw new ResourceNotFoundException("Story has not been found");
    }

    var story = findStoryById.get();
    story.setTitle(dto.title());
    story.setBody(dto.body());
    story.setPublicVisible(dto.publicVisible());
    storyRepo.save(story);

    return new StoryResponseDto(story.getId(),
        story.getTitle(),
        story.getBody(),
        story.isPublicVisible(),
        story.getCreatedAt(),
        new UserDto(story.getUser().getId(),
            story.getUser().getDisplayName(),
            story.getUser().getImage(),
            story.getUser().getCreatedAt()));

  }

  public ResponseEntity<?> deleteStoryById(Long id) {

    storyRepo.deleteById(id);

    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "Story deleted successfully");
    response.put("id", id);

    return new ResponseEntity<>(response, HttpStatus.OK);

  }

}
