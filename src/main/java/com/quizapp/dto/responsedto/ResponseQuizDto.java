package com.quizapp.dto.responsedto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseQuizDto {
	
	private Integer quizId;
	
	private String quizName;
	
	private Integer userId;
	
	private Integer topperId;
	
	private Integer totalParticipents;
	
	List<ResponseQuestionAnsDto> responseQuestionAnsDtos;

}
