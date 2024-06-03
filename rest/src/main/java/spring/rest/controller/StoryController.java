package spring.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.model.Story;
import spring.rest.service.StoryService;

@RestController
@RequestMapping("/api")
public class StoryController {

  final private StoryService storyService;

  public StoryController(StoryService storyService) {
    this.storyService = storyService;
  }

  @GetMapping("/stories")
  public List<Story> findAllStories() {

    return storyService.findAllStories();
  }
}
