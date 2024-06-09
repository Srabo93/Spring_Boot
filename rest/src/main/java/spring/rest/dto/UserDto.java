package spring.rest.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

public record UserDto(
        Long id,
        @JsonInclude(JsonInclude.Include.NON_NULL) String displayName,
        @JsonInclude(JsonInclude.Include.NON_NULL) String image,
        @JsonInclude(JsonInclude.Include.NON_NULL) Date createdAt) {
}
