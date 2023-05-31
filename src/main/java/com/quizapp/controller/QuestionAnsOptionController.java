package com.quizapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.repo.QuestionAndAnsRepo;
import com.quizapp.service.IQuestionAndAns;

@RestController
public class QuestionAnsOptionController {
	
	
	private static final Logger log = LoggerFactory.getLogger(QuestionAnsOptionController.class);

	
	@Autowired 
	private IQuestionAndAns questionAndAnsService;
	
	@DeleteMapping("/question/{questionId}")
	public ResponseEntity<String> deleteQuestionByIs(Integer questionId){
		log.info("deleteQuestionByIds QuestionId ="+questionId);
	
		boolean isQuestionDeleted = questionAndAnsService.deleteQuestionById(questionId);
		
		return (isQuestionDeleted)?new ResponseEntity<String>("Question Deleted",HttpStatus.OK):
			new ResponseEntity<String>("OOPS Unable to Delete",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
