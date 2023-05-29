package com.quizapp.dto.requestdto;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
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
	
	
}
