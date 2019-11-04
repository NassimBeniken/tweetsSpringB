package org.demo.tweets.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.demo.tweets.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    Indique que toutes les propriétés de la classe doivent être sérialisées et non null du coup je crois
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotBlank
    private String nickname;
    /*
        Permet de valider que cet attribut est une adresse mail valide
     */
    @Email
    private String mail;

    public User toEntity() {
        return User.builder()
                .mail(mail)
                .nickname(nickname)
                .build();
    }
}
