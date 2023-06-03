package com.quizapp.dto.responsedto;

import java.util.List;

import com.quizapp.entity.User;

import lombok.Data;

@Data
public class ResponseQuizDto {
	
	private Integer quizId;
	
	private String quizName;
	
	private User quizTopper;
	
	private Integer totalParticipents;
	

}
