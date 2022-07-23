package com.team2.cafein.service;

import com.team2.cafein.model.Bookmark;
import com.team2.cafein.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;


    @Transactional
    public List<Bookmark> getPosts(Long userId) {
        return bookmarkRepository.findAllByuserId(userId);
    }

    public Bookmark savePost(Long userId, Long postId) {
        //로그인 정보에서 userid 갖고 와서 +
        Bookmark bookmark = new Bookmark(userId, postId);
        bookmarkRepository.save(bookmark);
        return bookmark;

    }

    public String deleteBookmark(Long bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
        return "북마크 해제 완료";
    }
}

