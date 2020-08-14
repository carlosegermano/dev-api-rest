package com.cursorestapi.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursorestapi.apirest.model.ItemLike;

@Repository
public interface ItemLikeRepository extends JpaRepository<ItemLike, Long> {
}
