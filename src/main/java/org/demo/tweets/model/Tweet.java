package org.demo.tweets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demo.tweets.dto.TweetDto;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Tweet {

    @Id
    private String id;
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime modified;
    @NotBlank(message = "text must not be blank")
    @Size(max = 256, message = "tweet is max 256 characters")
    private String text;
    @NotNull
    private User user;
    @NotNull
    private Source source;
    @Transient
    private String  etag;

    @PersistenceConstructor
    public Tweet(String id, LocalDateTime created, LocalDateTime modified, String text, @NotNull User user, @NotNull Source source) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.text = text;
        this.user = user;
        this.source = source;
    }

    public TweetDto toDto() {
        return TweetDto.builder()
                .id(id)
                .text(text)
                .source(source)
                .user(user.toDto())
                .build();
    }
}
