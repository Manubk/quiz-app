package com.quizapp.dto.responsedto;

import lombok.Data;

@Data
public class ResponseUserDto {
	
	private Integer userId;
	
	private String userName;
	
	private String noOfQuizParticipated;
	
	private String noOfQuizCreated;
	
	private Integer totalScore;
	
}
