package com.team2.cafein.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Bookmark {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;

    private Long postId;

    public Bookmark(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
