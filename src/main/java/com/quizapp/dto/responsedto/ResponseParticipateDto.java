package com.quizapp.dto.responsedto;

import lombok.Data;

@Data
public class ResponseParticipateDto {
	
	private Integer participationId;
	
	private Integer userId;
	
	private Integer QuizId;
	
	private Integer totalPoints;
	
	private Integer gainedPoints;
	
	private Integer noOfAttempts;
	
	private String result;

}
