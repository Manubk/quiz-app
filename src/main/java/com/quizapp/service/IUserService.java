package com.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestUserDto;
import com.quizapp.dto.responsedto.ResponseUserDto;
import com.quizapp.entity.User;

@Service
public interface IUserService {
	
	/*
	 * Checking is User is present by userId
	 */
	public boolean isUserPresent(Integer userId);
	
	/*
	 * Checking is User is present by Email
	 */
	public boolean isUserPresent(String email);
	
	/*
	 * Creating a new User
	 */
	public boolean saveUser(RequestUserDto requestUserDto);
	
	/*
	 * Creating User
	 */
	public boolean saveUser(User user);

	/*
	 * Updating a exsisting user
	 */
	public boolean updateUser(RequestUserDto requestUserDto);
	
	/*
	 * Geting the user by User Id
	 */
	public ResponseUserDto findUserById(Integer userId);
	
	/*
	 * Deleting the userBy Id
	 */
	public boolean deleteUserById(Integer userId);
	
	/*
	 * Checking is user credentials is a valid
	 */
	public ResponseUserDto findUserByEmailAndPass(RequestUserDto requestUserDto);
	
	/*
	 * Getting the User from Id
	 */
	public User findInUserById(Integer userId);
	
	/*
	 * Getting All the Users
	 */
	public List<User> findAllUsers();
}
