package spring.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spring.rest.dto.StoryResponseDto;
import spring.rest.dto.UserDto;
import spring.rest.mapper.StoryMapper;
import spring.rest.model.Story;
import spring.rest.repository.StoryRepository;

public class StoryServiceTest {

  @Mock
  private StoryRepository storyRepo;
  @Mock
  private StoryMapper storyMapper;
  @InjectMocks
  private StoryService storyService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFindAllStories() {
    List<Story> stories = List.of(new Story(), new Story());
    List<StoryResponseDto> storyResponseDtos = List.of(
        new StoryResponseDto(1L, "title1", "body1", false, new Date(),
            new UserDto(1L, "disname", "/img/path", new Date())),
        new StoryResponseDto(2L, "title2", "body2", true, new Date(),
            new UserDto(2L, "disname2", "/img/path", new Date())));

    when(storyRepo.findAll()).thenReturn(stories);
    when(storyMapper.storyToStoryResponseDto(any(Story.class))).thenReturn(storyResponseDtos.get(0),
        storyResponseDtos.get(1));

    List<StoryResponseDto> result = storyService.findAllStories();

    assertEquals(2, result.size());
    verify(storyRepo, times(1)).findAll();
    verify(storyMapper, times(2)).storyToStoryResponseDto(any(Story.class));
  }

  @Test
  public void testFindAllPublicStories() {
    Story publicStory = new Story();
    publicStory.setPublicVisible(true);
    Story privateStory = new Story();
    privateStory.setPublicVisible(false);

    List<Story> stories = List.of(publicStory, privateStory);
    when(storyRepo.findAll()).thenReturn(stories);

    StoryResponseDto dto = new StoryResponseDto(1L, "Title", "Body", true, new Date(),
        new UserDto(1L, "User", "image.jpg", new Date()));
    when(storyMapper.storyToStoryResponseDto(any(Story.class))).thenReturn(dto);

    List<StoryResponseDto> result = storyService.findAllPublicStories();

    assertEquals(1, result.size());
    verify(storyRepo, times(1)).findAll();
    verify(storyMapper, times(1)).storyToStoryResponseDto(any(Story.class));

  }

}
