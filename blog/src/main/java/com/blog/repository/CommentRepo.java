package com.blog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.Comments;

@Repository
@Transactional
public interface CommentRepo extends JpaRepository<Comments, Integer> {

}
