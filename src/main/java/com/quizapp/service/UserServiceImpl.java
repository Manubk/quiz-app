package com.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestUserDto;
import com.quizapp.dto.responsedto.ResponseUserDto;
import com.quizapp.entity.User;
import com.quizapp.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public boolean saveUser(RequestUserDto requestUserDto) {
		log.info("saveUser "+requestUserDto.toString());
		
		User user = new User();
		
		BeanUtils.copyProperties(requestUserDto, user);
		
		User savedUser = userRepo.save(user);

		if(savedUser.getUserId() != null)
			return true;
		
		return false;
	}

	@Override
	public boolean updateUser(RequestUserDto requestUserDto) {
		log.info("updateUser "+requestUserDto.toString());
		
		Optional<User> user = userRepo.findById(requestUserDto.getUserId());
	
		BeanUtils.copyProperties(requestUserDto, user.get());
		
		try {
			userRepo.save(user.get());
			return true;
		} catch (Exception e) {
			log.error("updateUser");
			return false;
		}

	}

	@Override
	public ResponseUserDto findUserById(Integer userId) {
		log.info("findUserById Id -> "+userId);
		
		Optional<User> findById = userRepo.findById(userId);
		
		if(findById.isPresent()) {
			ResponseUserDto userDto = new ResponseUserDto();
			log.info("User = "+findById.get().toString());
			
			BeanUtils.copyProperties(findById.get(), userDto);
			log.info("userDto ="+userDto.toString());
			return userDto;
		}
		
		return null;
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		log.info("deleteUserById ID -> "+userId);
			ResponseUserDto userDto= findUserById(userId);
			
			if(userDto != null) {
				userRepo.deleteById(userId);
				return true;
			}
			
			return false;
	}

	@Override
	public boolean isUserPresent(Integer userId) {
		log.info("isUserPresent userId -> "+userId);
		return userRepo.existsById(userId);
		
	}

	@Override
	public boolean isUserPresent(String email) {
		log.info("isUserPresent Email -> "+email);
		return false;
	}

	@Override
	public ResponseUserDto findUserByEmailAndPass(RequestUserDto requestUserDto) {
		log.info("isUserValid -> "+requestUserDto.toString());
		
		User user = userRepo.findUserByEmailAndPass(requestUserDto.getUserEmail(), requestUserDto.getUserPass());
		
		if(user != null) {
			ResponseUserDto userDto = new ResponseUserDto();
			BeanUtils.copyProperties(user, userDto);
			return userDto;
		}
	
		return null;
	}

	@Override
	public User findInUserById(Integer userId) {
		log.info("findInUserById userId = "+userId);
		
		Optional<User> user = userRepo.findById(userId);
		
		if(user.isPresent())
			return user.get();
		
		return null;
	}

	@Override
	public boolean saveUser(User user) {
		log.info("saveUser userId = "+user.getUserId());
		try {
			User userSaved = userRepo.save(user);
			return true;
		} catch (Exception e) {
			log.error("saveUser userId = "+user.getUserId());
			return false;
		}
	
	}

	@Override
	public List<User> findAllUsers() {
		log.info("findAllUsers");
		
		List<User> users = userRepo.findAll();
		
		return users;
	}


	
	
}
