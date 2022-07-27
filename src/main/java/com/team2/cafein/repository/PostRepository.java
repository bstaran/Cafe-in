package com.team2.cafein.repository;

import com.team2.cafein.model.Post;
import com.team2.cafein.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);
}
