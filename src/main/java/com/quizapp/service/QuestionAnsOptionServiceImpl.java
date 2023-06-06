package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseQuizDto;
import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import com.quizapp.repo.QuestionAndAnsRepo;
import com.quizapp.repo.QuizRepo;

import jakarta.transaction.Transactional;

@Service
public class QuestionAnsOptionServiceImpl implements IQuestionAndAnsService {
	
	
	private static final Logger log = LoggerFactory.getLogger(QuestionAnsOptionServiceImpl.class);
	
	@Autowired
	private QuestionAndAnsRepo questionAndAnsRepo;
	
	@Autowired
	private QuizRepo quizRepo;

//	@Autowired
//	private IUserService userService;
	
	@Autowired
	private IQuizService quizService;
	
	/*
	 * Create a Single Question
	 */
	@Override
	public boolean createQuestion(@RequestBody RequestQuestionAnsDto requestQuestionAnsDto) {
		log.info("CreateQuestion requestDto = "+requestQuestionAnsDto.toString());
		
		 Quiz quiz = quizService.getInQuizById(requestQuestionAnsDto.getQuizId());
		
		QuestionAnsOption questionAnsOption = new QuestionAnsOption();
		
		BeanUtils.copyProperties(requestQuestionAnsDto, questionAnsOption);
		
		questionAnsOption.setQuiz(quiz);
		
		System.out.println(questionAnsOption.toString());
		
		QuestionAnsOption questionAns= questionAndAnsRepo.save(questionAnsOption);
		
		if(questionAns.getQuestionId() != null)
			return true;
		
		return false;
	}
	
	/*
	 * creating a multipal question
	 */
	@Override
	public boolean createQuestions(@RequestBody List<RequestQuestionAnsDto> requestQuestionAnsDtos) {
		log.info("createQuestions");
		
		Quiz quiz = quizService.getInQuizById(requestQuestionAnsDtos.get(0).getQuizId());
		
		List<QuestionAnsOption> questionAnswers = new ArrayList<>();
		
		for(RequestQuestionAnsDto reqQues : requestQuestionAnsDtos) {
			
			QuestionAnsOption questionAns = new QuestionAnsOption();
			BeanUtils.copyProperties(reqQues, questionAns);
			questionAns.setQuiz(quiz);
			questionAnswers.add(questionAns);
		}
		
		List<QuestionAnsOption> saveAllQuestion = questionAndAnsRepo.saveAll(questionAnswers);
		
		if(saveAllQuestion.size() == requestQuestionAnsDtos.size()) 
			return true;
		
		return false;
	}
	
	/*
	 * Deleting the Questions Based on Quiz ID
	 */
	@Override
	public boolean deleteQuestionByQuizId(Integer quizId) {
		log.info("deleteQuestionByQuizId quizId = "+quizId);
		
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		
		try {
			questionAndAnsRepo.deleteQuestionByQuizId(quiz.get());
			return true;
		} catch (Exception e) {
			log.info("deleteQuestionByQuizId");
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Update Question
	 */
	@Override
	public boolean updateQuestion(RequestQuestionAnsDto requestQuestionAnsDto) {
		log.info("updateQuestion RequestDto = "+requestQuestionAnsDto.toString());
		
		QuestionAnsOption questionAnsOption = new QuestionAnsOption();
		BeanUtils.copyProperties(requestQuestionAnsDto, questionAnsOption);
		
		try {
			questionAndAnsRepo.save(questionAnsOption);
			return true;
		} catch (Exception e) {
			log.error("updateQuestion QuestionId ="+requestQuestionAnsDto.getQuestionId());
			e.printStackTrace();
			return false;
		}
		
	}
	
	/*
	 * Updating the List Of Question
	 */
	@Override
	public boolean updateQuestions(List<RequestQuestionAnsDto> requestQuestionAnsDtos) {
		log.info("updateQuestions");
		//Pending
		
		 List<QuestionAnsOption> questions = getInAllTheQuestionByQuiz(requestQuestionAnsDtos.get(0).getQuizId());
		 
		 
		 
		 BeanUtils.copyProperties(requestQuestionAnsDtos,questions);
		 
		 requestQuestionAnsDtos.forEach((req) -> System.out.println(req.toString()));
		 questions.forEach((que) -> System.out.println(que.toString()));
		 
		try {
			List<QuestionAnsOption> saveAllQuestion = questionAndAnsRepo.saveAll(questions);
			return true;
		} catch (Exception e) {
			log.error("updateQuestions");
			e.printStackTrace();
			return false;
		}
		
	}

	/*
	 * Getting the Question Based on Id
	 */
	@Override
	public ResponseQuestionAnsDto getQuestionById(Integer questionId) {
		log.info("getQuestionById questionId = "+questionId);
		
		Optional<QuestionAnsOption> question = questionAndAnsRepo.findById(questionId);
		
		if(question.isPresent()) {
			ResponseQuestionAnsDto responseQuestion = new ResponseQuestionAnsDto();
			BeanUtils.copyProperties(question.get(), responseQuestion);
		    log.info(question.toString());
		    log.info(responseQuestion.toString());
			return responseQuestion;
		}
		return null;
	}

	/*
	 * Getting All The Question which are Related to Quiz
	 */
	@Override
	public List<ResponseQuestionAnsDto> getAllQuestionsByQuizId(Integer quizId) {
		log.info("getAllQuestonsByQuizId ");
		
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		
		List<QuestionAnsOption> allQuestions = questionAndAnsRepo.getAllQuestionsByQuizId(quiz.get());
		List<ResponseQuestionAnsDto> responseQuestionDto = new ArrayList<>();
		if(!allQuestions.isEmpty()) {
			for(QuestionAnsOption question : allQuestions) {
				ResponseQuestionAnsDto questionDto = new ResponseQuestionAnsDto();
				BeanUtils.copyProperties(question, questionDto);
				responseQuestionDto.add(questionDto);
			}
			return responseQuestionDto;
		}
			
		return null;
	}
	
	/*
	 * It tells is the given Queston is Present
	 */
	@Override
	public boolean isQuestionPresent(Integer questionId) {
		log.info("isQuestionPresent");
		return questionAndAnsRepo.existsById(questionId);
	}

	/*
	 * This will delete the Question By Id
	 */
	@Override
	public boolean deleteQuestionById(Integer questionId) {
		log.info("deleteQuestionById questionId = "+questionId);
		
		try {
			questionAndAnsRepo.deleteById(questionId);
			return true;
		} catch (Exception e) {
			log.error("deleteQuestionById");
			e.printStackTrace();
			return false;
		}
		
	}
	

	public boolean deleteQuestionByQuiz(Integer quizId){
		log.info("deleteQuestionByQuiz quizId = "+quizId);
		
		try {
			Quiz quiz = quizService.getInQuizById(quizId);
			
			 int deletedRows = questionAndAnsRepo.deleteQuestionByQuizId(quiz);
			 
			 log.info(deletedRows+" questions deleted");
			 
			 return true;
		} catch (Exception e) {
			log.error("deletedQuestionByQuiz quizId ="+quizId);
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public QuestionAnsOption getInQuestionById(Integer questionId) {
		log.info("getInQuestionById questionId = "+questionId);
		
		Optional<QuestionAnsOption> question = questionAndAnsRepo.findById(questionId);
		
		return question.get();
	}

	@Override
	public List<QuestionAnsOption> getInAllTheQuestionByQuiz(Integer quizId) {
		log.info("getInAllTheQuestionByQuiz quizId = "+quizId);
		
		Quiz quiz = quizService.getInQuizById(quizId);
		
	    List<QuestionAnsOption> questions = questionAndAnsRepo.getAllQuestionsByQuizId(quiz);
	    
		return questions;
	}

	@Override
	public List<ResponseQuestionAnsDto> getAllQuestions() {
		log.info("getAll");
		
		List<QuestionAnsOption> questions = questionAndAnsRepo.findAll();
	
		List<ResponseQuestionAnsDto> responseDtos = new ArrayList<>();
		
		for(QuestionAnsOption question : questions) {
			ResponseQuestionAnsDto responseDto = new ResponseQuestionAnsDto();
			
			BeanUtils.copyProperties(question, responseDto);
			responseDtos.add(responseDto);
		}
				
		return responseDtos;
	}
	
	

}
