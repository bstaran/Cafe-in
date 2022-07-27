package com.team2.cafein.controller;

import com.team2.cafein.config.auth.UserDetailsImpl;
import com.team2.cafein.dto.PostRequestDto;
import com.team2.cafein.dto.PostResponseDto;
import com.team2.cafein.dto.ResponseMessageDto;
import com.team2.cafein.dto.UpdatePostDto;
import com.team2.cafein.model.Post;
import com.team2.cafein.model.User;
import com.team2.cafein.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    // 게시글 등록 페이지 - 프론트에서 해결
    @GetMapping("/api/post")
    public PostRequestDto postForm(Model model) {
        PostRequestDto postRequestDto = new PostRequestDto();
        model.addAttribute("postRequestDto", postRequestDto);
        return postRequestDto;
    }

    // 게시글 디테일 조회 --> OK
    @GetMapping("/api/post/{postId}")
    public PostResponseDto get(@PathVariable Long postId) {
        return postService.findOne(postId);
    }

    // 게시글 등록 --> OK
    @PostMapping("/api/post")
    public ResponseMessageDto createPost(
            @RequestParam String cafeName,
            @RequestParam String content,
//            @RequestPart(value = "postRequestDto") PostRequestDto postRequestDto,
            @RequestPart(value = "file") MultipartFile file,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {

        /* 파일을 업로드 하지 않았을 경우 처리 */
        if (file.isEmpty()) {
            ResponseMessageDto responseMessageDto = new ResponseMessageDto();
            responseMessageDto.setStatus(false);
            responseMessageDto.setMessage("파일 첨부는 필수입니다");
            return responseMessageDto;
        }
        Long userId = userDetails.getUser().getId();
        return postService.createPost(cafeName, content, file, userId);
    }

    // 게시글 수정 페이지 --> 프론트에서 한다. 게시글 디테일 조회 API 활용
    @GetMapping("/api/post/{postId}/edit")
    public UpdatePostDto updateForm(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getUpdatePostDto(postId);
    }

    // 게시글 수정 --> 디테일하게 코드 이해를 해야한다. cafeName이랑 content만 수정이 안된다.
    @PutMapping("/api/post/{postId}")
    public ResponseMessageDto updatePost(@PathVariable Long postId,
                                         @RequestPart(value = "updatePostDto") UpdatePostDto updatePostDto,
                                         @RequestPart(value = "file") MultipartFile file,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception{

        Long userId = userDetails.getUser().getId();
        /* 파일을 업로드 하지 않았을 경우 처리 */
        if (file.isEmpty()) {
            UpdatePostDto.PostImageDto postImageDto = postService.getPostImageDto(postId);
            updatePostDto.setPostImageDto(postImageDto);
        }
        try {
            postService.updatePost(updatePostDto, file);
        } catch (Exception e) {
            log.error(e.getMessage());
//            bindingResult.reject("globalError", "상품 수정 중 에러가 발생하였습니다.");
//            return "adminitem/updateitemform";
        }
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        responseMessageDto.setStatus(true);
        responseMessageDto.setMessage("게시글 !! 수정 !! 성공");
        return responseMessageDto;
    }

    // 게시글 삭제 --> OK
    // PropertyValueException : not-null property references a null or transient value
    // --> @Transactional & 그리고 삭제 순서
    @DeleteMapping("/api/post/{postId}")
    public ResponseMessageDto deletePost(@PathVariable Long postId,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        String nickname = userDetails.getUser().getNickname();
        return postService.deletePost(postId, nickname);
    }

    // 게시글 전체 조회 --> OK
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts() {
        // Response 용 Dto를 만들어서 보내주자
        return postService.getPosts();
    }

    // 내가 올린 게시글 조회 -- 오류
    @GetMapping("/api/post/myposts")
    public List<Post> getMyPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//  Resolved [org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: failed to lazily initialize a collection of role: com.team2.cafein.model.User.posts, could not initialize proxy - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException:
//  failed to lazily initialize a collection of role: com.team2.cafein.model.User.posts, could not initialize proxy - no Session]
//        User user = userDetails.getUser();
//        return user.getPosts();

        return postService.getMyPosts(userDetails.getUser());
//  Failure while trying to resolve exception [org.springframework.http.converter.HttpMessageNotWritableException]
//  Cannot call sendError() after the response has been committed
    }
}
