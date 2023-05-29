package com.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestQuizDto;
import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;

@Service
public interface IQuizService {
	public boolean isQuizPresent(Integer quizId);
	public boolean createQuiz(RequestQuizDto requestQuizDto);
	public boolean deleteQuiz(Integer quizId);
	public boolean updateQuiz(RequestQuizDto requestQuizDto);
	public boolean addQuestionToQuiz(Integer quizId,QuestionAnsOption questionAnsOption);
	
}
