package com.vaibhav.service;

import java.util.List;
import java.util.Set;

import com.vaibhav.entity.Role;
import com.vaibhav.entity.User;

public interface UserService {
	User createUser(User user, Role role) throws Exception;
	
	User findUserByUserName(String userName) throws Exception;
	
	User findUserById(Long id) throws Exception;
	
	void deleteById(Long id) throws Exception;
	
	void deleteByUserName(String userName) throws Exception;
	
	User updateUser(User user) throws Exception;
	
	List<User> findAllUser();
}
