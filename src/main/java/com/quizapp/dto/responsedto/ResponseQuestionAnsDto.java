package com.quizapp.dto.responsedto;

import lombok.Data;

@Data
public class ResponseQuestionAnsDto {

	private Integer questionId;
	
	private String question;
	
	private String answer;
	
	private String option1;
	
	private String option2;
	
	private String option3;
	
	private String option4;
	
	private Integer quizId;
	
}
