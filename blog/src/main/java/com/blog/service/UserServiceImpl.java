package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.blog.exception.UserNotFoundException;
import com.blog.model.User;
import com.blog.pojo.UserPojo;
import com.blog.repository.UserRepo;
import com.blog.utils.BlogUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public void saveUser(UserPojo userpojo) {

		User user = new User();
		user.setEmail(userpojo.getEmail());
		user.setName(userpojo.getName());
		String hashedPassword = hashPassword(userpojo.getPassword());
		user.setPassword(hashedPassword);
		user.setUuid(BlogUtils.getRandomNumber());
		userRepo.save(user);
	}

	private String hashPassword(String password) {
		if (StringUtils.isEmpty(password))
			return null;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> user = userRepo.findAll();

		return user;
	}

	@Override
	public Boolean login(UserPojo userpojo) {

	User user=userRepo.getUser(userpojo.getEmail());
	System.out.println(user);
	if(user==null)
	{
		throw new UserNotFoundException("Invalid Email");
	}
	boolean result=validatePassword(userpojo.getPassword(), user.getPassword());
	return result;
	}

	private static boolean validatePassword(String enteredPassword, String savedPassword) {
		if (StringUtils.isEmpty(savedPassword) || StringUtils.isEmpty(enteredPassword))
			return false;
		return BCrypt.checkpw(enteredPassword, savedPassword);
	}
}
