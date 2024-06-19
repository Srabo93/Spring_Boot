package spring.rest.configuration;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.rest.mapper.StoryMapper;

@Configuration
public class MapperConfig {

  @Bean
  public StoryMapper storyMapper() {
    return Mappers.getMapper(StoryMapper.class);
  }

}
