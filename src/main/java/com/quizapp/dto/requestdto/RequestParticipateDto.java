package com.quizapp.dto.requestdto;

import java.util.List;

import lombok.Data;

@Data
public class RequestParticipateDto {
	
	private Integer participationId;
	
	private Integer userId;
	
	private Integer quizId;
	
	private List<RequestQuestionAnsDto> requestQuestionAns;
	
	
}
