package spring.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class StoryDtoTest {

  private static ObjectMapper mapper;

  static Stream<Arguments> provideStoryDtos() {
    Date now = new Date();

    return Stream.of(
        Arguments.of(new StoryDto(1L, "Title1", "Body1", true, now, 1L)),
        Arguments.of(new StoryDto(2L, "Title2", "Body2", false, now, 2L)));
  }

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
  }

  @ParameterizedTest
  @MethodSource("provideStoryDtos")
  public void testSerialization(StoryDto storyDto) throws JsonProcessingException {
    String json = mapper.writeValueAsString(storyDto);

    assertTrue(json.contains("\"id\":" + storyDto.id()));
    assertTrue(json.contains("\"title\":\"" + storyDto.title() + "\""));
    assertTrue(json.contains("\"body\":\"" + storyDto.body() + "\""));
    assertTrue(json.contains("\"publicVisible\":" + storyDto.publicVisible()));
    assertTrue(json.contains("\"createdAt\":" + storyDto.createdAt().getTime()));
    assertTrue(json.contains("\"userId\":" + storyDto.userId()));
  }

  @ParameterizedTest
  @MethodSource("provideStoryDtos")
  public void testDeserialization(StoryDto expectedStoryDto) throws JsonProcessingException {
    String json = mapper.writeValueAsString(expectedStoryDto);
    StoryDto actualStoryDto = mapper.readValue(json, StoryDto.class);

    assertEquals(expectedStoryDto.id(), actualStoryDto.id());
    assertEquals(expectedStoryDto.title(), actualStoryDto.title());
    assertEquals(expectedStoryDto.body(), actualStoryDto.body());
    assertEquals(expectedStoryDto.publicVisible(), actualStoryDto.publicVisible());
    assertEquals(expectedStoryDto.createdAt(), actualStoryDto.createdAt());
    assertEquals(expectedStoryDto.userId(), actualStoryDto.userId());
  }
}
