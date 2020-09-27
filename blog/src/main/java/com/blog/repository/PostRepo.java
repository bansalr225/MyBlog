package com.blog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blog.model.Post;

@Repository
@Transactional
public interface PostRepo extends JpaRepository<Post, Integer> {

	@Query(value = "select * from post where uuid= :postid" , nativeQuery = true)
	public Post getPostByUUID(int postid);


}
