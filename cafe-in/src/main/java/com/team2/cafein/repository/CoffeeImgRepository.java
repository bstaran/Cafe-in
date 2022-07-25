package com.team2.cafein.repository;

import com.team2.cafein.model.CoffeeImg;
import com.team2.cafein.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeImgRepository extends JpaRepository<CoffeeImg, Long> {

    List<CoffeeImg> findByPostOrderByCoffeeImgIdAsc(Post post);

    CoffeeImg findByPost(Post post);


}