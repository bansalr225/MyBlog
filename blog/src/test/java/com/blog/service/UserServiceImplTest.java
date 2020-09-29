package com.blog.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import com.blog.model.User;
import com.blog.pojo.UserPojo;
import com.blog.repository.UserRepo;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@RunWith(PowerMockRunner.class)
//@PrepareForTest(TokenUtils.class)
class UserServiceImplTest {

	@Mock
	private UserRepo userrepo;

	@InjectMocks
	private UserServiceImpl userserviceimpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
public UserServiceImplTest() {
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
	private UserPojo userPojoMock()
	{
		UserPojo userpojo=new UserPojo();
		userpojo.setEmail("abc@gmail.com");
		userpojo.setName("abc");
		userpojo.setPassword("abc");
		return userpojo;
		
	}


	@Test
	public void saveUserTest_Success()
	{
		boolean result=userserviceimpl.saveUser(userPojoMock());
		assertEquals(true, result);
	
	}
	
	@Test 
	public void loginUserTest_Success()
	{
		when(userrepo.getUser(any(String.class))).thenReturn(getUser().get(0));
		boolean result=userserviceimpl.login(userPojoMock());
		assertEquals(true, result);
	}
}
