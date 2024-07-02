package spring.rest.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class StoryUpdateDtoTest {

  private static Validator validator;

  @BeforeAll
  public static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testStoryUpdateDtoInitialization() {
    StoryUpdateDto dto = new StoryUpdateDto(1L, "Title", "Body", true, 1L);

    assertEquals(1L, dto.id());
    assertEquals("Title", dto.title());
    assertEquals("Body", dto.body());
    assertTrue(dto.publicVisible());
    assertEquals(1L, dto.userId());
  }

  @Test
  public void testStoryUpdateDtoNotNullValidation() {
    StoryUpdateDto dto = new StoryUpdateDto(null, "Title", "Body", true, 1L);

    Set<ConstraintViolation<StoryUpdateDto>> violations = validator.validate(dto);

    assertEquals(1, violations.size());
    assertEquals("The Story id is null", violations.iterator().next().getMessage());
  }

}
