package com.quizapp.dto.requestdto;

import java.util.List;

import lombok.Data;

@Data
public class RequestQuizDto  {
	
	private Integer quizId;
	
	private String quizName;
	
	private Integer userId;
	
	private Integer topperId;
	
	private Integer totalParticipents;
	
	private List<Integer> questionsAnsIds;
}
