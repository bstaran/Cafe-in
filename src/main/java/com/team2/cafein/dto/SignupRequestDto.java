package com.team2.cafein.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class SignupRequestDto {
    private Long id;

    private String nickname;

    private String email;

    private String password;
}
