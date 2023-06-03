package com.quizapp.service;

import java.awt.print.PrinterGraphics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.requestdto.RequestQuizDto;
import com.quizapp.dto.responsedto.ResponseParticipateDto;
import com.quizapp.dto.responsedto.ResponseQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseQuizDto;
import com.quizapp.entity.Participate;
import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;

@Service
public interface IQuizService {
	
	/*
	 * Checking is the given Quiz Is Present
	 */
	public boolean isQuizPresent(Integer quizId);
	
	/*
	 * Creating a new Quiz
	 */
	public boolean createQuiz(RequestQuizDto requestQuizDto);
	
	/*
	 * Deleting a Quiz By QuizID
	 */
	public boolean deleteQuizById(Integer quizId);

	/*
	 * Update a exsisting Quiz
	 */
	public boolean updateQuiz(RequestQuizDto requestQuizDto);
	
	/*
	 * Add a question to the quiz
	 */
	public boolean addQuestionToQuiz(Integer quizId,RequestQuestionAnsDto questionAnsOptionDto);
	
	/*
	 * Add a List Of Questions to Quiz
	 */
	public boolean addQuestionsTOQuiz(Integer quizId,List<RequestQuestionAnsDto> questionAnsOptionDtos);
	
	/*
	 * Delete a Question from Quiz using Quesion Id
	 */
	public boolean deleteQuestionFromQuiz(Integer questionId);
	
	/*
	 * Delete All Questions Related to quiz
	 */
	public boolean deleteAllQuestionFromQuiz(Integer quizId);
	
	/* Get the Quiz by Quiz Id */
	public ResponseQuizDto getQuizById(Integer quizId);
	
	/*
	 * Get Quiz By Id
	 */
	public Quiz getInQuizById(Integer quizId);
	
	/*
	 * Get all the Quiz Created By user
	 */
	public List<ResponseQuizDto> getAllTheQuizByUserId(Integer userId);
	
	/*
	 * Get all the questions for respective quiz
	 */
	public List<ResponseQuestionAnsDto> getAllQuestionByQuizId(Integer quizId);
	
	/*
	 * updation the quiz values based on every participation
	 */
	public boolean updateQuizByParticipate(Participate participate);
	
}
