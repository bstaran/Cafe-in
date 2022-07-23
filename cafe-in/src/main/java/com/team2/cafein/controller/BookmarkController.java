package com.team2.cafein.controller;


import com.team2.cafein.model.Bookmark;
import com.team2.cafein.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookmarkController {
    private final BookmarkService bookmarkService;

    //모든 북마크 포스트 갖고오기
    @GetMapping("/api/bookmark/{userId}")
    public List<Bookmark> getPosts(@PathVariable Long userId){
        return bookmarkService.getPosts(userId);
    }

    @GetMapping("/api/bookmark/{postId}") // 유저 아이디 어디서 갖고오냐 ...
    public Bookmark savePost(@PathVariable Long postId,Long userId){
        return bookmarkService.savePost(userId,postId);
    }

//    // 댓글 작성
//    @PostMapping("/api/reply")
//    public String createReply(@RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        // 로그인 되어 있는 ID
//        if (userDetails != null) {
//            Long userId = userDetails.getUser().getId();
//            String username = userDetails.getUser().getUsername();
//            ReplyService.createReply(requestDto, username, userId);
//            return "댓글 작성 완료";
//        }
//        return "로그인이 필요한 기능입니다.";
//    }


    @DeleteMapping("/api/bookmark/{bookmarkId}")
    public String deleteBookmark(@PathVariable Long bookmarkId)  {
        return bookmarkService.deleteBookmark(bookmarkId);
    }

}
// 북마크 보기. = POST ID 의 리스트를 주는것이 아니고 for 문을 돌려서 POST 데이터를 줘야한다
// 북마크 설정은 POST ID 와 USER ID 만 저장하면 된다.
// 북마크 제거는 북마크 ID 로 제거 하면 끝이난다.