package com.quizapp.dto.requestdto;

import lombok.Data;

@Data
public class RequestUserDto {
	
	private Integer userId;
	
	private String userName;
	
	private String userEmail;
	
	private String userPass;
	
}
