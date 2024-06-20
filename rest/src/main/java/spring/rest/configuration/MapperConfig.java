package spring.rest.configuration;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.rest.mapper.StoryMapper;
import spring.rest.mapper.UserMapper;

@Configuration
public class MapperConfig {

  @Bean
  public StoryMapper storyMapper() {
    return Mappers.getMapper(StoryMapper.class);
  }

  @Bean
  public UserMapper userMapper() {
    return Mappers.getMapper(UserMapper.class);
  }
}
