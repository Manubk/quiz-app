package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseQuestionAnsDto;
import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;
import com.quizapp.repo.QuestionAndAnsRepo;
import com.quizapp.repo.QuizRepo;

import jakarta.transaction.Transactional;

@Service
public class QuestionAnsOptionServiceImpl implements IQuestionAndAns {
	
	
	private static final Logger log = LoggerFactory.getLogger(QuestionAnsOptionServiceImpl.class);
	
	@Autowired
	private QuestionAndAnsRepo questionAndAnsRepo;
	
	@Autowired
	private QuizRepo quizRepo;

	/*
	 * Create a Single Question
	 */
	@Override
	public boolean createQuestion(RequestQuestionAnsDto requestQuestionAnsDto) {
		log.info("CreateQuestion");
		
		QuestionAnsOption questionAnsOption = new QuestionAnsOption();
		BeanUtils.copyProperties(requestQuestionAnsDto, questionAnsOption);
		
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
	public boolean createQuestions(List<RequestQuestionAnsDto> requestQuestionAnsDtos) {
		log.info("createQuestions");
		List<QuestionAnsOption> questionAnswers = new ArrayList<>();
		
		for(RequestQuestionAnsDto reqQues : requestQuestionAnsDtos) {
			
			QuestionAnsOption questionAns = new QuestionAnsOption();
			BeanUtils.copyProperties(reqQues, questionAns);
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
		log.info("deleteQuestionByQuizId");
		
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
		log.info("updateQuestion");
		
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

		List<QuestionAnsOption> questionAnswers = new ArrayList<>();

		for (RequestQuestionAnsDto reqQues : requestQuestionAnsDtos) {

			QuestionAnsOption questionAns = new QuestionAnsOption();
			BeanUtils.copyProperties(reqQues, questionAns);
			questionAnswers.add(questionAns);
		}
		try {
			List<QuestionAnsOption> saveAllQuestion = questionAndAnsRepo.saveAll(questionAnswers);
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
		log.info("getQuestionById");
		
		Optional<QuestionAnsOption> question = questionAndAnsRepo.findById(questionId);
		
		if(question.isPresent()) {
			ResponseQuestionAnsDto responseQuestion = new ResponseQuestionAnsDto();
			BeanUtils.copyProperties(question, responseQuestion);
			System.out.println(question);
			System.out.println(responseQuestion);
			return responseQuestion;
		}
		return null;
	}

	/*
	 * Getting All The Questiong which are Related to Quiz
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
		log.info("deleteQuestionById");
		
		try {
			questionAndAnsRepo.deleteById(questionId);
			return true;
		} catch (Exception e) {
			log.error("deleteQuestionById");
			e.printStackTrace();
			return false;
		}
		
	}
	
	

}
