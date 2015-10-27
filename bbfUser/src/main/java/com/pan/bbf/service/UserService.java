package com.pan.bbf.service;

import java.util.List;

import com.pan.bbf.user.entities.User;

public interface UserService {

	String insert(User user);
	
	User findById(String id);

	List<User> findAllUser();

	void deleteUser(String userId);
	
	void updateUser(User user);
	
	void updateUserSelective(User user);
}
