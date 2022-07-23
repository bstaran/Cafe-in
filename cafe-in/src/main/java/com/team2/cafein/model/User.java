package com.team2.cafein.model;

import com.team2.cafein.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(SignupRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
    }
}
