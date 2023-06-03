package com.quizapp.dto.responsedto;

import lombok.Data;

@Data
public class ResponseUserDto {
	
	private Integer userId;
	
	private String userName;
	
	private Integer noOfQuizParticipated;
	   			  
	private Integer noOfQuizCreate;
	
	private Integer totalScore;
	
}
