package org.demo.tweets.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.demo.tweets.model.Source;
import org.demo.tweets.model.Tweet;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data //Permet de générer tous les getters et setter sans les ecrire (grace a lombok)t
@Builder
@AllArgsConstructor
public class TweetDto {

    public static final String ZONE_DATE_TIME_FORMAT = "";

    @NotBlank(message = "text must not be  blank")
    @Size(max = 256, message = "tweet is max  256 characters")
    String text;
    @NotNull
    UserDto user;
    String id;
    @NotNull
    private Source source;
    @JsonFormat(pattern = ZONE_DATE_TIME_FORMAT)
    ZonedDateTime created;
    @JsonFormat(pattern = ZONE_DATE_TIME_FORMAT)
    ZonedDateTime modified;

    public Tweet toEntity() {
        return Tweet.builder()
                .id(id)
                .text(text)
                .source(source)
                .user(user.toEntity())
                .build();
    }
}
