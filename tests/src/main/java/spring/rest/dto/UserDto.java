package spring.rest.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;

public record UserDto(
@NotNull(message = "User id can not be null")
Long id,
@JsonInclude(JsonInclude.Include.NON_NULL) String displayName,
@JsonInclude(JsonInclude.Include.NON_NULL) String image,
@JsonInclude(JsonInclude.Include.NON_NULL) Date createdAt) implements UserDtoI {

}
