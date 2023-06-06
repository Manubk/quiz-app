package com.quizapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseQuestionAnsDto;
import com.quizapp.repo.QuestionAndAnsRepo;
import com.quizapp.service.IQuestionAndAnsService;



import java.util.List;

@RestController
public class QuestionAnsOptionController {
	
	
	private static final Logger log = LoggerFactory.getLogger(QuestionAnsOptionController.class);

	
	@Autowired 
	private IQuestionAndAnsService questionAndAnsService;
	
	@PostMapping("/question")
	public ResponseEntity<String> createQuestion(@RequestBody RequestQuestionAnsDto requestQuestionAnsDto ){
		log.info("createQuestion requestDto ="+requestQuestionAnsDto.toString());
		
		boolean isQuestionSaved = questionAndAnsService.createQuestion(requestQuestionAnsDto);
		
		return (isQuestionSaved)?new ResponseEntity<String>("Saved Sucessfully",HttpStatus.ACCEPTED):
			new ResponseEntity<String>("Unable to save ",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/questions")
	public ResponseEntity<String> createQuestions(@RequestBody List<RequestQuestionAnsDto> requestQuestionAnsDtos){
		log.info("createQuestions");
		
		requestQuestionAnsDtos.forEach((req) -> System.out.println(req.toString()));
		
		boolean isQuestionsSaved = questionAndAnsService.createQuestions(requestQuestionAnsDtos);
		
		return (isQuestionsSaved)?new ResponseEntity<String>("Saved Sucessfully",HttpStatus.OK):
			new ResponseEntity<String>("Unable to save ",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@GetMapping("/question/{questionId}")
	public ResponseEntity<ResponseQuestionAnsDto> GetQuestionById(Integer questionId){
		log.info("getQuestionById questionId = "+questionId);
		
		ResponseQuestionAnsDto question = questionAndAnsService.getQuestionById(questionId);
		
		return new ResponseEntity<ResponseQuestionAnsDto>(question,HttpStatus.OK);
	}
	
	@GetMapping("/question/quiz/{quizId}")
	public ResponseEntity<List<ResponseQuestionAnsDto>> getAllQuestionByQuizId(Integer quizId){
			log.info("getAllQuestionByQuizId quizId = "+quizId);
			
			List<ResponseQuestionAnsDto> allQuestions = questionAndAnsService.getAllQuestionsByQuizId(quizId);
			
			return new ResponseEntity<List<ResponseQuestionAnsDto>>(allQuestions,HttpStatus.OK);
	}
	
	@GetMapping("/questions")
	public ResponseEntity<List<ResponseQuestionAnsDto>> getAllQuestions(){
		log.info("getAllQuestions");
		
		List<ResponseQuestionAnsDto> allQuestions = questionAndAnsService.getAllQuestions();
		
		return new ResponseEntity<List<ResponseQuestionAnsDto>>(allQuestions,HttpStatus.OK);
	}
	
	@PutMapping("/question")
	public ResponseEntity<String> updateQuestion(@RequestBody RequestQuestionAnsDto requestQuestionAnsDto){
		log.info("updateQuestion");
		
		boolean isQuestionCreated = questionAndAnsService.createQuestion(requestQuestionAnsDto);
		
		return (isQuestionCreated)?new ResponseEntity<String>("Saved SucessFull",HttpStatus.OK):
			new ResponseEntity<String>("Unable To Save",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/questions")
	public ResponseEntity<String> updateQuestions(@RequestBody List<RequestQuestionAnsDto> requestQuestionAnsDtos){
		log.info("updatQuestions");
		
		boolean isQuestionsSaved = questionAndAnsService.updateQuestions(requestQuestionAnsDtos);
		
		return (isQuestionsSaved)?new ResponseEntity<String>("Saved SucessFully",HttpStatus.OK):
			new ResponseEntity<String>("Unable to Save",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@DeleteMapping("/question/{questionId}")
	public ResponseEntity<String> deleteQuestionByIs(Integer questionId){
		log.info("deleteQuestionByIds QuestionId ="+questionId);
	
		boolean isQuestionDeleted = questionAndAnsService.deleteQuestionById(questionId);
		
		return (isQuestionDeleted)?new ResponseEntity<String>("Question Deleted",HttpStatus.OK):
			new ResponseEntity<String>("OOPS Unable to Delete",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/question/quiz/{quizId}")
	public ResponseEntity<String> deleteQuestionByQuiz(Integer quizId){
		log.info("deleteQuestionByQuiz quizId = "+quizId);
		
		 boolean isQuestionDeleted= questionAndAnsService.deleteQuestionByQuizId(quizId);
		 
		 return (isQuestionDeleted)?new ResponseEntity<String>("Deleted SucessFully" ,HttpStatus.OK):
			 new ResponseEntity<String>("Unable to Delete",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
