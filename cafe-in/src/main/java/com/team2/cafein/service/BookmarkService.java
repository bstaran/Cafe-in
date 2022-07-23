package com.team2.cafein.service;

import com.team2.cafein.dto.ResponseMessageDto;
import com.team2.cafein.model.Bookmark;
import com.team2.cafein.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public List<Post> getBooks(Long userId) {

        // 로그인 되어있는 userId로 Bookmark 테이블에서 select로 리스트 배열 받아오기
        List<Bookmark> bookmarks = bookmarkRepository.getBooks(userId);

        List<Post> responsePosts = new ArrayList<>();   // 여기는 응답할 게시글 목록을 위한 리스트 선언

        for (Bookmark bookmark : bookmarks) {
            Long postId = bookmark.getPostId();
            Post post = postRepository.findByPostId(postId);
            responsePosts.add(post);
        }
        return responsePosts;

    }

    public Bookmark savePost(Long userId, Long postId) {

        //로그인 정보에서 userid 갖고 와서 +
        Bookmark bookmark = new Bookmark(userId, postId);
        bookmarkRepository.save(bookmark);
        return bookmark;

    }

    public ResponseMessageDto deleteBookmark(Long bookmarkId) {
        // 비지니스 로직 구간
        bookmarkRepository.deleteById(bookmarkId);
        // ---------------------------

        // 응답 객체 만들기
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        responseMessageDto.setOk("ok");
        responseMessageDto.setMessage("북마크 해제 완료");
        return responseMessageDto;
    }
}

