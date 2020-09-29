package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.model.User;
import com.blog.pojo.UserPojo;

@Service
public interface UserService {

	public boolean saveUser(UserPojo user);

	public List<User> getAllUsers();

	public Boolean login(UserPojo user);

}
