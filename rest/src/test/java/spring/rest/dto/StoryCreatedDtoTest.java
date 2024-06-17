package spring.rest.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;
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

  static Stream<Arguments> provideCreatedStoryDtos() {

    return Stream.of(
        Arguments.of(new StoryCreateDto("Title1", "Body1", true, 1L)),
        Arguments.of(new StoryCreateDto("Title2", "Body2", false, 2L)));
  }

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
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
  public void testDeSerialization(StoryCreateDto expectedStoryDto) throws JsonProcessingException {

    String json = mapper.writeValueAsString(expectedStoryDto);
    StoryDto actualStoryDto = mapper.readValue(json, StoryDto.class);

    assertEquals(expectedStoryDto.title(), actualStoryDto.title());
    assertEquals(expectedStoryDto.body(), actualStoryDto.body());
    assertEquals(expectedStoryDto.publicVisible(), actualStoryDto.publicVisible());
    assertEquals(expectedStoryDto.userId(), actualStoryDto.userId());
  }

  @Test
  public void testTitleNotNull() {
    StoryCreateDto dto = new StoryCreateDto(null, "Body1", true, 1L);

    Set<ConstraintViolation<StoryCreateDto>> violations = validator.validate(dto);

    boolean foundException = false;

    for (var violation : violations) {
      if ("The Title can not be null".equals(violation.getMessage())) {
        foundException = true;
      }
    }

    assertTrue(foundException, "Exception Title not null found");

  }
}
