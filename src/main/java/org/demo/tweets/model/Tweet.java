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
/*
    Provient de Lombok aussi je crois
    Permet de générer un constructeur sans arguments
 */
@NoArgsConstructor
/*
    Provient de Lombok
    Permet de générer un constructeur avec tous les arguments
 */
@AllArgsConstructor
/*
    Provient de Lombok
    Contient les annotations @Getter, @Setter, @ToString, @EqualsAndHashCode et @RequiredArgsConstructor
    permet de générer toutes ces méthodes sans rien faire, tout est déjà écrit
 */
@Data
/*
    Pour Mongo
    Permet de dire que cette classe va être mappée dans la base de données (sans information supplémentaire,
    la collection sera tweet. On peut changer ça en mettant @Document(collection = "lesTweets"))
 */
@Document
public class Tweet {

    /*
        Specifie que c'est la clé primaire
     */
    @Id
    private String id;
    /*
        Permet de faire du suivi pour savoir qui et quand une donnée a été modifiée
     */
    @CreatedDate
    private LocalDateTime created;
    /*
        Permet de faire du suivi pour savoir qui et quand une donnée a été modifiée
     */
    @LastModifiedDate
    private LocalDateTime modified;
    @NotBlank(message = "text must not be blank")
    @Size(max = 256, message = "tweet is max 256 characters")
    private String text;
    @NotNull
    private User user;
    @NotNull
    private Source source;
    /*
        Signifie que cet attribut ne doit pas être persisté en base de données
     */
    @Transient
    private String  etag;

    /*
        Precise que c'est ce constructeur qui sera utilisé pour créer les entités dans Mongo (évite les
        ambuiguités avec d'autres constructeurs)
     */
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
