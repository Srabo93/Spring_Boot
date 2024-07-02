package spring.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import spring.rest.dto.StoryCreateDto;
import spring.rest.dto.StoryDto;
import spring.rest.dto.StoryResponseDto;
import spring.rest.dto.StoryUpdateDto;
import spring.rest.model.Story;

@Mapper
public interface StoryMapper {
  StoryMapper INSTANCE = Mappers.getMapper(StoryMapper.class);

  StoryDto storyToStoryDto(Story story, Long userId);

  StoryResponseDto storyToStoryResponseDto(Story story);

  List<StoryResponseDto> storiesToStoryResponseDtos(List<Story> stories);

  StoryUpdateDto storyToStoryUpdateDto(Story story, Long userId);

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  Story storyDtoToStory(StoryCreateDto dto);
}
