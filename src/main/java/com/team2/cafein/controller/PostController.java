package com.team2.cafein.controller;

import com.team2.cafein.dto.PostRequestDto;
import com.team2.cafein.dto.ResponseMessageDto;
import com.team2.cafein.model.Post;
import com.team2.cafein.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 등록
    @PostMapping("/api/post")
    public ResponseMessageDto createPost(@RequestBody PostRequestDto requestDto, MultipartFile file) throws Exception {

        /* 파일을 업로드 하지 않았을 경우 처리 */
        if (file.isEmpty()) {
        }
        return postService.createPost(requestDto, file);
    }

//    // 게시글 수정
//    @PutMapping("/api/post/{postId}")
//    public ResponseMessageDto updatePost() {
//
//    }
//
//    // 게시글 삭제
//    @DeleteMapping("/api/post/{postId}")
//    public ResponseMessageDto deletePost(Long postId) {
//
//    }


    // 게시글 전체 조회
    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

//    // 내가 올린 게시글 조회
//    @GetMapping("/api/post/myposts")
//    public List<Post> getMyPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return postService.getMyPosts(userDetails.getUser());

//        // 아니면 -> 이게 맞는듯하다.
//        User user = userDetails.getUser();
//        return user.getPosts();
//    }
}
