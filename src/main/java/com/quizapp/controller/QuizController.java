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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.requestdto.RequestQuizDto;
import com.quizapp.dto.responsedto.ResponseQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseQuizDto;
import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import com.quizapp.repo.QuizRepo;
import com.quizapp.service.IQuestionAndAns;
import com.quizapp.service.IQuizService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;


@RestController
public class QuizController {
	
	
	private static final Logger log = LoggerFactory.getLogger(QuizController.class);
	
	@Autowired
	private IQuizService quizService;
	
	@Autowired
	private IQuestionAndAns questionAndAns;
	
	/*
	 * Create quiz quiz
	 */
	@PostMapping("/quiz")
	public ResponseEntity<String> saveQuiz(@RequestBody RequestQuizDto quizDto ){
		log.info("saveQuiz QuizDto = "+quizDto.toString());
		
		boolean isQuizCreated= quizService.createQuiz(quizDto);
		
		return (isQuizCreated)?new ResponseEntity<String>("Saved Sucessfull",HttpStatus.OK):
			new ResponseEntity<String>("Saved Unsucessfull",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/*
	 * Get quiz by its ID
	 */
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<ResponseQuizDto> getQuizById(Integer quizId){
		log.info("getQuizById QuizId = "+quizId);
	
    	ResponseQuizDto responseQuiz = quizService.getQuizById(quizId);
	
		return new ResponseEntity<ResponseQuizDto>(responseQuiz,HttpStatus.OK);
	}
	
	
	/*
	 * Get all the Quiz created By User by UserId
	 */
	@GetMapping("/quiz/user/{userId}")
	public ResponseEntity<List<ResponseQuizDto>> getAllQuizByUserId(Integer userId){
		log.info("getAllQuizByUserId userId = "+userId);
		
		List<ResponseQuizDto> quizDtos = quizService.getAllTheQuizByUserId(userId);
		
		return new ResponseEntity<List<ResponseQuizDto>>(quizDtos,HttpStatus.OK);
		
		
	}
	
//	/*
//	 * Get all the Questions by QuizId
//	 */
//	@GetMapping("/quiz/{quizId}/questions")
//	public ResponseEntity<List<ResponseQuestionAnsDto>> getAllQuestionsByQuizId(Integer quizId){
//		log.info("getAllQuestionsByQuizId");
//		
//	
//		List<ResponseQuestionAnsDto> allQuestionsByQuizId = questionAndAns.getAllQuestionsByQuizId(quizId);
//		
//		return new ResponseEntity<List<ResponseQuestionAnsDto>>(allQuestionsByQuizId,HttpStatus.OK);
//	}
	
	/*
	Delete the Quiz created by UserId
	*/
	@DeleteMapping("/quiz/user/{userId}")
	public ResponseEntity<String> deleteAllQuizByUserId(Integer userId){
		log.info("deleteAllQuizByUserId userId = "+userId);
		
		boolean isQuizDeleted = quizService.deleteQuizByUserId(userId);
		
		return (isQuizDeleted)?new ResponseEntity<String>("Deleted SucessFull",HttpStatus.OK):
			new ResponseEntity<String>("Unable to Delete",HttpStatus.BAD_REQUEST);
	}
	
	/*
	Delete the Quiz created by UserId using quizID
	*/
	@DeleteMapping("/quiz/{quizId}")
	public ResponseEntity<String> deleteAllQuizByQuizId(Integer quizId){
		log.info("deleteAllQuizByQuizId quizId = "+quizId);
		boolean isDeleted = quizService.deleteQuizById(quizId);
		
		return (isDeleted)?new ResponseEntity<String>("Deleted SucessFull",HttpStatus.OK):
			new ResponseEntity<String>("Deleted UnSucessFull",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	/*
//	 * Add the Question to Quiz
//	 */
//	@PostMapping("/quiz/{quizId}/question")
//	public ResponseEntity<String> addQuestionToQuiz( Integer quizId,@RequestBody RequestQuestionAnsDto requestQuestonDto){
//		log.info("addQuestionToQuiz");
//		boolean isQuestionAdded = quizService.addQuestionToQuiz(quizId, requestQuestonDto);
//		
//		return (isQuestionAdded)?new ResponseEntity<String>("Question Added",HttpStatus.ACCEPTED):
//			new ResponseEntity<String>("OOPs Unable to Add",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
//	/*
//	 * Add the Questions to Quiz
//	 */
//	@PostMapping("/quiz/{quizId}/questions")
//	public ResponseEntity<String> addQuestionsToQuiz(@PathVariable Integer quizId,@RequestBody List<RequestQuestionAnsDto> requestQuestionAnsDtos){
//		log.info("addQuestionsToQuiz");
//		
////		return new ResponseEntity<String>(requestQuestionAnsDtos.toString(),HttpStatus.OK);
//		boolean isQuestionsAdded = quizService.addQuestionsTOQuiz(quizId, requestQuestionAnsDtos);
//		
//		return (isQuestionsAdded)?new ResponseEntity<String>("Questions Added",HttpStatus.ACCEPTED):
//			new ResponseEntity<String>("OOPs Unable to Add",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
//	/*
//	 * Delete the Questions from Quiz
//	 */
//	@DeleteMapping("/quiz/{quizId}/question")
//	public ResponseEntity<String> deleteQuestionFromQuiz(@PathVariable Integer quizId){
//		log.info("deleteQuestionFromQuiz");
//		
//		boolean isQuestionDeleted = quizService.deleteQuestionFromQuiz(quizId);
//		
//		return(isQuestionDeleted)?new ResponseEntity<String>("Question Deleted",HttpStatus.OK):
//			new ResponseEntity<String>("OOPs Unable To Delete",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
}
