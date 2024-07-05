package spring.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spring.rest.dto.StoryCreateDto;
import spring.rest.dto.StoryResponseDto;
import spring.rest.dto.StoryUpdateDto;
import spring.rest.dto.UserDto;
import spring.rest.exceptions.ResourceNotFoundException;
import spring.rest.mapper.StoryMapper;
import spring.rest.model.Story;
import spring.rest.model.User;
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
  void findAllStories_WhenStoriesExists_ReturnsStoryResponseDto() {
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
  void findAllPublicStories_WhenPublicExists_ReturnStoryResponseDto() {
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

  @Test
  void findStoryById_WhenStoryExists_ReturnStoryResponseDto() {
    Story story = new Story();
    Long storyId = 1L;
    story.setId(storyId);
    when(storyRepo.findById(storyId)).thenReturn(Optional.of(story));

    StoryResponseDto expectedDto = new StoryResponseDto(storyId, "Title", "Body", true, new Date(),
        new UserDto(1L, "User", "image.jpg", new Date()));
    when(storyRepo.findById(storyId)).thenReturn(Optional.of(story));
    when(storyMapper.storyToStoryResponseDto(any(Story.class))).thenReturn(expectedDto);

    StoryResponseDto result = storyService.findStoryById(storyId);

    assertNotNull(result);
    assertEquals(expectedDto, result);
    verify(storyRepo).findById(storyId);
    verify(storyMapper).storyToStoryResponseDto(story);
  }

  @Test
  void findStoryById_WhenStoryDoesNotExist_ThrowsResourceNotFoundException() {
    Long storyId = 1L;
    when(storyRepo.findById(storyId)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> storyService.findStoryById(storyId));

    assertEquals("Story not found with id: " + storyId, exception.getMessage());
    verify(storyRepo).findById(storyId);
    verifyNoInteractions(storyMapper);
  }

  @Test
  void createStory_WithValidDto() {
    Date fixedDate = new Date();

    StoryCreateDto createDto = new StoryCreateDto("Title", "Body", true, 1L);

    User user = User.builder()
        .id(1L)
        .displayName("name")
        .image("/path/img")
        .createdAt(fixedDate)
        .build();

    Story expectedStory = Story.builder()
        .id(1L)
        .title("Test")
        .body("Body")
        .publicVisible(true)
        .user(user)
        .createdAt(fixedDate)
        .build();

    StoryResponseDto expectedResponseDto = new StoryResponseDto(1L, "Title",
        "Body", true, fixedDate,
        new UserDto(1L, "name", "/path/img", fixedDate));

    when(storyMapper.storyDtoToStory(createDto)).thenReturn(expectedStory);
    when(storyRepo.save(any(Story.class))).thenReturn(expectedStory);
    when(storyMapper.storyToStoryResponseDto(expectedStory)).thenReturn(expectedResponseDto);

    StoryResponseDto result = storyService.createStory(createDto);

    assertNotNull(result);
    assertEquals(expectedResponseDto, result);

    verify(storyMapper).storyDtoToStory(createDto);
    verify(storyRepo).save(expectedStory);
    verify(storyMapper).storyToStoryResponseDto(expectedStory);
  }

  @Test
  void replaceStoryById_ExistingStory_ShouldUpdateAndReturnDto() {
    long storyId = 1L;
    StoryUpdateDto updateDto = new StoryUpdateDto(storyId, "Updated Title", "Updated Body", true, 1L);

    User user = User.builder()
        .id(1L)
        .displayName("name")
        .image("/path/img")
        .createdAt(new Date())
        .build();

    Story existingStory = new Story(storyId, "Original Title", "Original Body", false, new Date(), user);
    UserDto userDto = new UserDto(1L, "name", "/path/img", new Date());
    StoryResponseDto updatedResponse = new StoryResponseDto(storyId, "Updated Title", "Updated Body", true, new Date(),
        userDto);

    when(storyRepo.findById(updateDto.id())).thenReturn(Optional.of(existingStory));
    when(storyRepo.save(any(Story.class))).thenAnswer(invocation -> invocation.getArgument(0));
    when(storyMapper.storyToStoryResponseDto(any(Story.class))).thenReturn(updatedResponse);

    StoryResponseDto result = storyService.replaceStoryById(updateDto);

    assertNotNull(result);
    assertEquals(updateDto.id(), result.id());
    assertEquals("Updated Title", result.title());
    assertEquals("Updated Body", result.body());
    assertTrue(result.publicVisible());
    assertEquals(existingStory.getCreatedAt(), result.createdAt());
    assertEquals(user.getId(), result.user().id());
    assertEquals(user.getDisplayName(), result.user().displayName());
    assertEquals(user.getImage(), result.user().image());
    assertEquals(user.getCreatedAt(), result.user().createdAt());

    verify(storyRepo).findById(updateDto.id());
    verify(storyRepo).save(existingStory);
    verify(storyMapper).storyToStoryResponseDto(existingStory);
  }

  @Test
  void replaceStoryById_NonExistingStory_ShouldThrowException() {
    long nonExistingStoryId = 999L;
    StoryUpdateDto updateDto = new StoryUpdateDto(nonExistingStoryId, "Title",
        "Body", true, 3L);

    when(storyRepo.findById(nonExistingStoryId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> storyService.replaceStoryById(updateDto));
    verify(storyRepo).findById(nonExistingStoryId);
    verify(storyRepo, never()).save(any(Story.class));
  }
}
