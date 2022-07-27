package com.team2.cafein.model;

import com.team2.cafein.base.Timestamped;
import com.team2.cafein.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Post extends Timestamped {

    @Id
    @GeneratedValue
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String cafeName;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    private int bookmarkCount;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post(PostRequestDto requestDto, String fileName, String filePath) {
        this.cafeName = requestDto.getCafeName();
        this.content = requestDto.getContent();
        this.fileName = fileName;
        this.filePath = filePath;
        this.bookmarkCount = 0;
//        setUser(user);
    }

    //== 연관관계 (편의) 메서드==// 양방향 연관관계 세팅을 까먹지않고 할수있는 장점
    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    // 북마크 증가
    public void addCount() {
        this.bookmarkCount++;
    }
}
