package com.pan.bbf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pan.bbf.common.exception.BBFException;
import com.pan.bbf.service.UserService;
import com.pan.bbf.user.entities.User;
import com.pan.bbf.user.entities.dao.UserMapper;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public String insert(User user) {
		if(user == null){
			throw new BBFException("entity can not be null");
		}
		userMapper.insert(user);
		return user.getId();
	}

	@Override
	public User findById(String id) {
		return userMapper.findById(id);
	}

	@Override
	public List<User> findAllUser() {
		return userMapper.findAll();
	}

	@Override
	public void deleteUser(String userId) {
		userMapper.deleteById(userId);
	}

	@Override
	public void updateUser(User user) {
		userMapper.update(user);
	}

	@Override
	public void updateUserSelective(User user) {
		userMapper.updateSelective(user);		
	}
	
	
}
