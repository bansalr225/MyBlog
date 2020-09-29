package com.blog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.blog.model.Post;
import com.blog.model.User;
import com.blog.pojo.PostPojo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.utils.BlogUtils;

@RunWith(PowerMockRunner.class)
class PostServiceImplTest {

	@Mock
	private PostRepo postrepo;

	@Mock
	private UserRepo userrepo;

	@InjectMocks
	private PostServiceImpl postserviceimpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	public PostServiceImplTest() {
		// TODO Auto-generated constructor stub
	}

	private List<User> getUser() {
		List<User> getUserList = new ArrayList<User>();
		User user = new User();
		user.setEmail("abc@gmail.com");
		user.setId(1234);
		user.setName("abc");
		user.setPassword("$2a$10$haxrUXQOYIYh/kJgyzFuseSL38IqdBT1i9AMoeqHeY2HM70BJD/yy");
		user.setUuid(122222);
		getUserList.add(user);

		return getUserList;
	}

	private Post postMock() {
		Post post = new Post();
		post.setContent("abbb");
		post.setStatus("active");
		post.setUuid(BlogUtils.getRandomNumber());
		post.setUser(getUser().get(0));
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss", Locale.ENGLISH);
		Date date;
		try {
			date = format.parse(timeStamp);

			post.setCreatedTime(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return post;
	}

	@Test
	public void createPost() {

		when(userrepo.getUserByUUID(anyInt())).thenReturn(getUser().get(0));
		postrepo.save(postMock());

	}

}
