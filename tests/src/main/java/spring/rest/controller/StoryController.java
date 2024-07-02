package spring.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.rest.dto.StoryCreateDto;
import spring.rest.dto.StoryResponseDto;
import spring.rest.dto.StoryUpdateDto;
import spring.rest.mapper.StoryMapper;
import spring.rest.service.StoryService;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

  final private StoryService storyService;

  public StoryController(StoryService storyService, StoryMapper storyMapper) {
    this.storyService = storyService;
  }

  @GetMapping()
  public List<StoryResponseDto> findAllStories() {

    return storyService.findAllStories();
  }

  @PostMapping()
  public StoryResponseDto createStory(@Valid @RequestBody StoryCreateDto dto) {

    return storyService.createStory(dto);
  }

  @GetMapping("/public")
  public List<StoryResponseDto> findAllPublicStories() {

    return storyService.findAllPublicStories();
  }

  @GetMapping("/{id}")
  public StoryResponseDto findStoryById(@PathVariable Long id) {

    return storyService.findStoryById(id);
  }

  @PutMapping("/{id}")
  public StoryResponseDto replaceStoryById(@Valid @RequestBody StoryUpdateDto dto) {

    return storyService.replaceStoryById(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> patchStoryById(@PathVariable Long id) {

    return storyService.deleteStoryById(id);
  }

}
