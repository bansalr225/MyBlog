package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.pojo.CommentPojo;
import com.blog.pojo.MultipleComment;
import com.blog.pojo.PostPojo;
import com.blog.service.PostService;
import com.blog.utils.RestResponse;

@RestController
@RequestMapping("/blog")
public class PostController {

	@Autowired
	private PostService service;

	@PostMapping("/createPost")
	public RestResponse<String> createPost(@RequestBody PostPojo post) {

		boolean b=service.createPost(post);
		if(b)
			return new RestResponse<String>(true, "200", null,"Post Created");
		return null;

	}
	
	@PostMapping("/postComment")
	public RestResponse<String> postComment(@RequestBody CommentPojo post)
	{
		boolean b=service.postComment(post);
		if(b)
			return new RestResponse<String>(true, "200", null,"Comment Created");
		return null;
		
	}
	@PostMapping("/multiplePostComment")
	public RestResponse<String> multiplePostComment(@RequestBody MultipleComment comment)
	{
		service.multiplePostComment(comment);
		return new RestResponse<String>(true, "200", null,"Multiple Comment Created");
		
	}
	
	
}
