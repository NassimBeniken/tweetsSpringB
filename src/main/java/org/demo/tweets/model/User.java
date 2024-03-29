package org.demo.tweets.model;

import lombok.Builder;
import lombok.Data;
import org.demo.tweets.dto.UserDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class User {
    @NotBlank
    private String nickname;
    /*
        Permet de valider que cet attribut est une adresse mail valide
     */
    @Email
    private String mail;

    public UserDto toDto() {
        return UserDto.builder()
                .mail(mail)
                .nickname(nickname)
                .build();
    }
}
