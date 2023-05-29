package com.quizapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.requestdto.RequestQuizDto;
import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import com.quizapp.service.IQuestionAndAns;
import com.quizapp.service.IQuizService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;


@RestController
public class QuizController {
	
	
	private static final Logger log = LoggerFactory.getLogger(QuizController.class);
	
	@Autowired
	private IQuizService quizService;
	
	@Autowired
	private IQuestionAndAns questionAndAnsService;
	
	/*
	 * Create quiz quiz
	 */
	@PostMapping("/quiz")
	public ResponseEntity<String> saveQuiz(@RequestBody RequestQuizDto quizDto ){
		log.info("saveQuiz");
		
		boolean isQuizCreated= quizService.createQuiz(quizDto);
		
		return (isQuizCreated)?new ResponseEntity<String>("Saved Sucessfull",HttpStatus.OK):
			new ResponseEntity<String>("Saved Unsucessfull",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/*
	 * Get quiz by its ID
	 */
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<Quiz> getQuizById(Integer quizId){
		
		return null;
	}
	
	
	/*
	 * Get all the Quiz created By User by UserId
	 */
	@GetMapping("/quiz/user/{userId}")
	public ResponseEntity<List<Quiz>> getAllQuizByUserId(Integer userId){
		log.info("getAllQuixByUserId");
	
		return null;
		
		
	}
	
	/*
	Delete the Quiz created by UserId
	*/
	@DeleteMapping
	public ResponseEntity<String> deleteAllQuizByUserId(Integer userId){
		return null;
	}
	
	
}
