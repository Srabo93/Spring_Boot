package spring.rest.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StoryResponseDtoTest {

  private ObjectMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
  }

  @Test
  void testSerialization() throws JsonProcessingException {
    UserDto user = new UserDto(1L, "John Doe", "/path/to/img", new Date());
    StoryResponseDto dto = new StoryResponseDto(1L, "Title", "Body", true, new Date(), user);

    String json = mapper.writeValueAsString(dto);

    assertNotNull(json);

    assertEquals(1L, dto.id());
    assertEquals("Title", dto.title());
    assertEquals("Body", dto.body());
    assertEquals(true, dto.publicVisible());
    assertNotNull(dto.createdAt());
    assertEquals(user, dto.user());
  }

  @Test
  void testDeserialization() throws JsonProcessingException {
    String json = "{\"id\":1,\"title\":\"Title\",\"body\":\"Body\",\"publicVisible\":true,\"createdAt\":\"2024-06-17T12:00:00Z\",\"user\":{\"id\":1,\"displayName\":\"John Doe\",\"image\":\"/path/to/img\"}}";

    StoryResponseDto dto = mapper.readValue(json, StoryResponseDto.class);

    assertNotNull(dto);
    assertEquals(1L, dto.id());
    assertEquals("Title", dto.title());
    assertEquals("Body", dto.body());
    assertEquals(true, dto.publicVisible());
    assertNotNull(dto.createdAt());
    assertNotNull(dto.user());
    assertEquals(1L, dto.user().id());
    assertEquals("John Doe", dto.user().displayName());
    assertEquals("/path/to/img", dto.user().image());
  }
}
