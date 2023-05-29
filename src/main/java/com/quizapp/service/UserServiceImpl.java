package com.quizapp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.entity.User;
import com.quizapp.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public boolean save(User user) {
		User save = userRepo.save(user);
		
		if(save.getUserId() != null)
			return true;
		
		return false;
	}

	@Override
	public boolean update(User user) {
		return save(user);
	}

	@Override
	public User findUserById(Integer userId) {
		Optional<User> findById = userRepo.findById(userId);
		
		return findById.get();
	}

	@Override
	public boolean deleteUserById(Integer userId) {
			User user= findUserById(userId);
			
			if(user != null) {
				userRepo.deleteById(userId);
				return true;
			}
			
			return false;
	}

	@Override
	public boolean isUserPresent(Integer userId) {
		log.info("isUserPresent");
		return userRepo.existsById(userId);
		
	}

}
