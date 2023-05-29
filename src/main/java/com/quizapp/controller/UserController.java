package com.quizapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.entity.User;
import com.quizapp.service.IUserService;

@RestController
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<String>updateUser(@RequestBody User user){
		
		boolean saved = userService.save(user);
		
		
		return (true)?new ResponseEntity<>("Saved Sucessfully"
				,HttpStatus.ACCEPTED):new ResponseEntity<>("Saved UnSucessFull",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getAllUsers(@PathVariable Integer userId){
		
		if (userService.isUserPresent(userId)) {
			User user = userService.findUserById(userId);
			return  new ResponseEntity<User>(user, HttpStatus.OK);
					
		}
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		logger.debug(null);
		boolean saved = userService.save(user);
		
		return (saved)?new ResponseEntity<>("Updated Sucessfully"
				,HttpStatus.ACCEPTED):new ResponseEntity<>("Updated UnSucessfull",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer userId){
		
		if(userService.isUserPresent(userId)) {
			boolean deleted = userService.deleteUserById(userId);
			return (deleted)?new ResponseEntity<String>("Deleted Sucessfull",HttpStatus.OK):
				new ResponseEntity<String>("Unable To Delete",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("No Record To Delete",HttpStatus.BAD_REQUEST);
	}
	
}