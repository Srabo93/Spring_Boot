
package spring.rest.dto;

import java.util.Date;

public record StoryResponseDto(
        String title,
        String body,
        boolean publicVisible,
        Date createdAt,
        UserDto user) {

    public StoryResponseDto(String title, String body, boolean publicVisible, Date createdAt, UserDto user) {
        this.title = title;
        this.body = body;
        this.publicVisible = publicVisible;
        this.createdAt = createdAt;
        this.user = user;
    }

}
