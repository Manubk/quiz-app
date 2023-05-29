package com.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseQuestionAnsDto;
import com.quizapp.entity.QuestionAnsOption;

@Service
public interface IQuestionAndAns {
	public boolean createQuestion(RequestQuestionAnsDto requestQuestionAnsDto);
	public boolean createQuestions(List<RequestQuestionAnsDto> requestQuestionAnsDtos);
	public boolean deleteQuestionByQuizId(Integer quizId);
	public boolean deleteQuestionById(Integer questionId);
	public boolean updateQuestion(RequestQuestionAnsDto requestQuestionAnsDto);
	public boolean updateQuestions(List<RequestQuestionAnsDto> requestQuestionAnsDtos);
	public ResponseQuestionAnsDto getQuestionById(Integer questionId);
	public List<ResponseQuestionAnsDto> getAllQuestionsByQuizId(Integer quizId);
	public boolean isQuestionPresent(Integer questionId);
}
