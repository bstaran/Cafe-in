package com.team2.cafein.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkRequestDto {
    private Long id;
    private Long userId;
    private Long postId;
}
