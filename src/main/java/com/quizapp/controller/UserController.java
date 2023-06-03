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

import com.quizapp.dto.requestdto.RequestUserDto;
import com.quizapp.dto.responsedto.ResponseUserDto;
import com.quizapp.entity.User;
import com.quizapp.service.IUserService;

@RestController
public class UserController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<String>updateUser(@RequestBody RequestUserDto requestUserDto){
		log.info("updateUser user ="+requestUserDto.toString());
		boolean saved = userService.saveUser(requestUserDto);
		
		return (true)?new ResponseEntity<>("Saved Sucessfully"
				,HttpStatus.ACCEPTED):new ResponseEntity<>("Saved UnSucessFull",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<ResponseUserDto> getAllUsers(@PathVariable Integer userId){
		
		if (userService.isUserPresent(userId)) {
			ResponseUserDto userDto = userService.findUserById(userId);
			
			return  new ResponseEntity<ResponseUserDto>(userDto, HttpStatus.OK);
					
		}
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody RequestUserDto requestUserDto){
		
		boolean saved = userService.saveUser(requestUserDto);
		
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
