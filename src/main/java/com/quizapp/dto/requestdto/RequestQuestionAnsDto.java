package com.quizapp.dto.requestdto;

import lombok.Data;

@Data
public class RequestQuestionAnsDto {
	
	private Integer questionId;
	
	private String question;
	
	private String answer;
	
	private String option1;
	
	private String option2;
	
	private String option3;
	
	private String option4;
	
	private Integer quizId;
	
	private String submitedAns;
	
	
}
