package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.UserNotFoundException;
import com.blog.io.springsecurityjwt.MyUserDetailsService;
import com.blog.model.User;
import com.blog.pojo.UserPojo;
import com.blog.service.UserService;
import com.blog.springsecurityjwt.util.JwtUtil;
import com.blog.utils.RestResponse;

@RestController()
@RequestMapping("/blog")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/signUp")
	public RestResponse<String> saveUser(@RequestBody UserPojo user) {
		userService.saveUser(user);
		return new RestResponse<String>(false, "201", null, "Record Saved");
	}
	
	@PostMapping("/login")
	public RestResponse<String> login(@RequestBody UserPojo authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
	    	throw new UserNotFoundException("Invalid Credentials");
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
	

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return new RestResponse<String>(true, "200", "jwt= "+jwt, "Successfully LoggedIn");

	}
	
	

	@GetMapping("/getAllUsers")
	public RestResponse<List<User>> getAllUsers()
	{
		List<User> user=userService.getAllUsers();
		if(user!=null)
		return new RestResponse<List<User>>(false, "200", user, "success");
		return new RestResponse<List<User>>(false, "200", null, "not found");

	}
}
