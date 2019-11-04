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

/*
    Provient de Lombok
    Contient les annotations @Getter, @Setter, @ToString, @EqualsAndHashCode et @RequiredArgsConstructor
    permet de générer toutes ces méthodes sans rien faire, tout est déjà écrit
 */
@Data
/*
    Provient de Lombok
    Permet de créer des instances de notre classe sans avoir besoin de constructeur
    en utiisant seulement le code suivant :
    Tweet newTweet = Tweet.builder()
        .id(1)
        .created("date")
        .modified("date")
        .text("text du tweet")
        .user(User)
        .source(Source)
        .etag("etag")
        .build();

    On peut creer des copies ou quasi copies de nos instances en utilisant @Builder(toBuilder = true)
    Ca permet de pouvoir faire ça :
    Tweet.TweetBuilder tweetBuilder = newTweet.toBuilder(); //Ici du coup on a exactement la même chose que newTweet
    Tweet copieTweet = tweetBuilder.id(2).build(); //Ici du coup on a le meme que newTweet mais avec id=2
 */
@Builder
/*
    Provient de Lombok
    Permet de générer un constructeur avec tous les arguments
 */
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
    /*
        Décide comment l'attribut sera sérialisé
     */
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
