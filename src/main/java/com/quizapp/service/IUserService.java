package com.quizapp.service;

import org.springframework.stereotype.Service;

import com.quizapp.entity.User;

@Service
public interface IUserService {
	public boolean isUserPresent(Integer userId);
	public boolean save(User user);
	public boolean update(User user);
	public User findUserById(Integer userId);
	public boolean deleteUserById(Integer userId);
}
