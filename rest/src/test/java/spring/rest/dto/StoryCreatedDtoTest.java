package spring.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class StoryCreatedDtoTest {

  private static ObjectMapper mapper;
  private static Validator validator;

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  static Stream<Arguments> provideCreatedStoryDtos() {
    return Stream.of(
        Arguments.of(new StoryCreateDto("Title1", "Body1", true, 1L)),
        Arguments.of(new StoryCreateDto("Title2", "Body2", false, 2L)));
  }

  @ParameterizedTest
  @MethodSource("provideCreatedStoryDtos")
  public void testSerialization(StoryCreateDto storyDto) throws JsonProcessingException {
    String json = mapper.writeValueAsString(storyDto);

    assertTrue(json.contains("\"title\":\"" + storyDto.title() + "\""));
    assertTrue(json.contains("\"body\":\"" + storyDto.body() + "\""));
    assertTrue(json.contains("\"publicVisible\":" + storyDto.publicVisible()));
    assertTrue(json.contains("\"userId\":" + storyDto.userId()));
  }

  @ParameterizedTest
  @MethodSource("provideCreatedStoryDtos")
  public void testDeserialization(StoryCreateDto expectedStoryDto) throws JsonProcessingException {
    String json = mapper.writeValueAsString(expectedStoryDto);
    StoryCreateDto actualStoryDto = mapper.readValue(json, StoryCreateDto.class);

    assertEquals(expectedStoryDto.title(), actualStoryDto.title());
    assertEquals(expectedStoryDto.body(), actualStoryDto.body());
    assertEquals(expectedStoryDto.publicVisible(), actualStoryDto.publicVisible());
    assertEquals(expectedStoryDto.userId(), actualStoryDto.userId());
  }

  @Test
  public void testTitleNotNull() {
    StoryCreateDto dto = new StoryCreateDto(null, "Body1", true, 1L);

    Set<ConstraintViolation<StoryCreateDto>> violations = validator.validate(dto);

    assertTrue(violations.stream().anyMatch(v -> "The Title can not be null".equals(v.getMessage())),
        "Expected exception for Title not null");
  }

  @Test
  public void testTitleNotEmpty() {
    StoryCreateDto dto = new StoryCreateDto("", "Body1", true, 1L);

    Set<ConstraintViolation<StoryCreateDto>> violations = validator.validate(dto);

    assertTrue(violations.stream().anyMatch(v -> "The Title cant be empty".equals(v.getMessage())),
        "Expected exception for Title not empty");
  }

  @Test
  public void testBodyNotNull() {
    StoryCreateDto dto = new StoryCreateDto("Title", null, true, 1L);

    Set<ConstraintViolation<StoryCreateDto>> violations = validator.validate(dto);

    assertTrue(violations.stream().anyMatch(v -> "The Body can not be null".equals(v.getMessage())),
        "Expected exception for Body not null");
  }

  @Test
  public void testBodyNotEmpty() {
    StoryCreateDto dto = new StoryCreateDto("Title", "", true, 1L);

    Set<ConstraintViolation<StoryCreateDto>> violations = validator.validate(dto);

    assertTrue(violations.stream().anyMatch(v -> "The Body cant be empty".equals(v.getMessage())),
        "Expected exception for Body not empty");
  }

  @Test
  public void testUserIdNotNull() {
    StoryCreateDto dto = new StoryCreateDto("Title", "Body", true, null);

    Set<ConstraintViolation<StoryCreateDto>> violations = validator.validate(dto);

    assertTrue(violations.stream().anyMatch(v -> "The user id cant be null".equals(v.getMessage())),
        "Expected exception for userId not empty");
  }
}
