package com.team2.cafein.repository;

import com.team2.cafein.model.Bookmark;
import com.team2.cafein.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {


    List<Bookmark> findByUserId(Long userId);

//    List<Bookmark> findAllByUserId(Long userId);'
//    List<Bookmark> findAllByUserIdOrderBy(Long userId);
    //
}
