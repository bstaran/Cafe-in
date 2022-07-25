package com.team2.cafein.service;

import com.team2.cafein.dto.ResponseMessageDto;
import com.team2.cafein.model.Bookmark;
import com.team2.cafein.model.Post;
import com.team2.cafein.repository.BookmarkRepository;
import com.team2.cafein.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;

    @Transactional
    public List<Post> getPosts(Long userId) {

        // 로그인 되어있는 userId로 Bookmark 테이블에서 select로 리스트 배열 받아오기
        List<Bookmark> bookmarks = bookmarkRepository.findAllByUserId(userId);

        List<Post> responsePosts = new ArrayList<>();   // 여기는 응답할 게시글 목록을 위한 리스트 선언

        for (Bookmark bookmark : bookmarks) { //for 문을 돌리면서 POST ID 에대응되는 post의 내용을 List<post> 에 담아서 리턴
            Long postId = bookmark.getPostId();
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new NullPointerException("ID값 확인해주세여"));
            responsePosts.add(post);
        }
        return responsePosts;

    }

    public ResponseMessageDto savePost(Long userId, Long postId) {

        //로그인 정보에서 userid 갖고 와서 +
        Bookmark bookmark = new Bookmark(userId, postId);
        bookmarkRepository.save(bookmark);

        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        responseMessageDto.setStatus(true);//불리언으로 받아와서 셋팅 . 기본값을 false 로 놓고 ?
        if (responseMessageDto.isStatus()==true) {
            responseMessageDto.setMessage("등록성공");
        } else {
            responseMessageDto.setMessage("등록실패");

        }
        return responseMessageDto;
    }

        public ResponseMessageDto deleteBookmark (Long bookmarkId){
            // 비지니스 로직 구간
            bookmarkRepository.deleteById(bookmarkId);
            // ---------------------------

            // 응답 객체 만들기
            ResponseMessageDto responseMessageDto = new ResponseMessageDto();
            responseMessageDto.setStatus(true);
            if (responseMessageDto.isStatus()==true) {
                responseMessageDto.setMessage("등록성공");
            } else {
                responseMessageDto.setMessage("등록실패");

            }
            return responseMessageDto;
        }
    }


