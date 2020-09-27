package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.pojo.CommentPojo;
import com.blog.pojo.MultipleComment;
import com.blog.pojo.PostPojo;

@Service
public interface PostService {

	public boolean createPost(PostPojo post);

	public boolean postComment(CommentPojo post);

	public void multiplePostComment(MultipleComment comment);

}
