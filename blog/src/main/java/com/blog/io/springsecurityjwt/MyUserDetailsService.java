package com.blog.io.springsecurityjwt;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.exception.UserNotFoundException;
import com.blog.repository.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    com.blog.model.User user=userDao.getUser(s);
    if(user==null)
    {
    	throw new UserNotFoundException("Invalid Credentials");
    }
     return new User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }
}