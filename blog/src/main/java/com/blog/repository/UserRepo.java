package com.blog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.model.User;
import com.blog.pojo.UserPojo;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query(value = "select * from user where email= :email" , nativeQuery = true)
	public User getUser(String email);

	@Query(value = "select * from user where uuid= :userId" , nativeQuery = true)
	public User getUserByUUID(int userId);



}
