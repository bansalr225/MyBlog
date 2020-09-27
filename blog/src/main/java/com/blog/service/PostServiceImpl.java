package com.blog.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.enums.StatusEnum;
import com.blog.exception.UserNotFoundException;
import com.blog.model.Comments;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.pojo.CommentPojo;
import com.blog.pojo.CommentPost;
import com.blog.pojo.MultipleComment;
import com.blog.pojo.PostPojo;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.utils.BlogUtils;

@Service
public class PostServiceImpl implements PostService {

	private static final String ACTIVE = "active";

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentrepo;

	@Override
	public boolean createPost(PostPojo postPojo) {

		User user = userRepo.getUserByUUID(postPojo.getUserId());
		if (user == null) {
			throw new UserNotFoundException("Invalid Id");
		}
		Post post = new Post();
		post.setContent(postPojo.getContent());
		post.setStatus(ACTIVE);
		post.setUuid(BlogUtils.getRandomNumber());
		post.setUser(user);
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss", Locale.ENGLISH);
		Date date;
		try {
			date = format.parse(timeStamp);

			post.setCreatedTime(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		postRepo.save(post);
		return true;

	}

	@Override
	public boolean postComment(CommentPojo commentpost) {
		User user = userRepo.getUserByUUID(commentpost.getUserid());
		if (user == null) {
			throw new UserNotFoundException("Invalid UserId");
		}
		Post post = postRepo.getPostByUUID(commentpost.getPostid());
		if (post == null) {
			throw new UserNotFoundException("Invalid PostId");
		}

		Comments comment = new Comments();
		comment.setPost(post);
		comment.setUser(user);
		comment.setStatus(ACTIVE);
		comment.setUuid(BlogUtils.getRandomNumber());
		comment.setComment(commentpost.getComment());
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		comment.setCreatedTime(timeStamp);
		commentrepo.save(comment);
		return true;
	}

	@Override
	public void multiplePostComment(MultipleComment multiplecomment) {
		Post post = postRepo.getPostByUUID(multiplecomment.getPostid());
		if (post == null) {
			throw new UserNotFoundException("Invalid PostId");
		}
		List<CommentPost> listcomment = multiplecomment.getCommentpost();
		for (CommentPost l : listcomment) {
			Comments comment = new Comments();
			User user = userRepo.getUserByUUID(l.getUserid());
			if (user == null) {
				throw new UserNotFoundException("Invalid UserId");
			}

			comment.setPost(post);
			comment.setUser(user);
			comment.setStatus(ACTIVE);
			comment.setUuid(BlogUtils.getRandomNumber());
			comment.setComment(l.getComment());
			String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
			comment.setCreatedTime(timeStamp);
			commentrepo.save(comment);

		}
	}

}
