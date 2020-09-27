package com.blog.pojo;

import java.util.List;

public class MultipleComment {
	private int postid;
	private List<CommentPost> commentpost;

	public MultipleComment() {
		// TODO Auto-generated constructor stub
	}

	public MultipleComment(int postid, List<CommentPost> commentpost) {
		super();
		this.postid = postid;
		this.commentpost = commentpost;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public List<CommentPost> getCommentpost() {
		return commentpost;
	}

	public void setCommentpost(List<CommentPost> commentpost) {
		this.commentpost = commentpost;
	}

}
