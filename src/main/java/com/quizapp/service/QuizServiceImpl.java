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
import com.quizapp.dto.requestdto.RequestQuizDto;
import com.quizapp.dto.responsedto.ResponseQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseQuizDto;
import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;
import com.quizapp.repo.QuestionAndAnsRepo;
import com.quizapp.repo.QuizRepo;

@Service
public class QuizServiceImpl implements IQuizService {
	
	private static final Logger log = LoggerFactory.getLogger(QuizServiceImpl.class);
	
	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuestionAndAnsRepo questionAnsRepo;

	@Override
	public boolean isQuizPresent(Integer quizId) {
		log.info("isQuizPresent");
		return  quizRepo.existsById(quizId);
	}

	@Override
	public boolean createQuiz(RequestQuizDto requestQuizDto) {
		log.info("createQuiz");
		
		Quiz quiz = new Quiz();
		BeanUtils.copyProperties(requestQuizDto, quiz);
		log.info(quiz.toString());
		Quiz quizSaved = quizRepo.save(quiz);
		
		if(quizSaved.getQuizId() != null)
			return true;
		
		return false;
	}

	@Override
	public boolean deleteQuizById(Integer quizId) {
		log.info("deleteQuix");
		
		try {
			quizRepo.deleteById(quizId);
			return true;
		} catch (Exception e) {
			log.info("deleteQuizById");
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateQuiz(RequestQuizDto requestQuizDto) {
		log.info("updateQuiz");
		
		Quiz quiz = new Quiz();
		BeanUtils.copyProperties(requestQuizDto, quiz);
		log.info(quiz.toString());
		
		try {
			quizRepo.save(quiz);
			return true;
		} catch (Exception e) {
			log.error("updateQuiz");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addQuestionToQuiz(Integer quizId, RequestQuestionAnsDto questionAnsOptionDto) {
		log.info("addQuestionToQuiz");
		
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		
		
		QuestionAnsOption questionAns = new QuestionAnsOption();
		
		BeanUtils.copyProperties(questionAnsOptionDto, questionAns);
		
		questionAns.setQuiz(quiz.get());
		
		log.info(questionAns.toString());
		System.out.println(questionAns.toString());
		
		try {
			questionAnsRepo.save(questionAns);
			return true;
		} catch (Exception e) {
			log.error("addQuestionToQuiz");
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean addQuestionsTOQuiz(Integer quizId, List<RequestQuestionAnsDto> questionAnsOptionDtos) {
		log.info("addQuestionsToQuiz");
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		
		List<QuestionAnsOption> questionAns = new ArrayList<>();
		
		for(RequestQuestionAnsDto questionDto : questionAnsOptionDtos) {
			
			QuestionAnsOption question = new QuestionAnsOption();
			BeanUtils.copyProperties(questionDto, question);
			question.setQuiz(quiz.get());
			questionAns.add(question);
		}
		try {
			questionAnsRepo.saveAll(questionAns);
			return true;
		} catch (Exception e) {
			log.error("addQuestionsToQuiz");
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean deleteQuestionFromQuiz(Integer questionId) {
		log.info("deleteQuestionFromQuiz");
		
		try {
			questionAnsRepo.deleteById(questionId);
			return true;
		} catch (Exception e) {
			log.error("deleteQuestionFromQuiz");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAllQuestionFromQuiz(Integer quizId) {
		log.info("deleteAllQuestionFromQuiz");
		
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		try {
			questionAnsRepo.deleteQuestionByQuizId(quiz.get());
			return true;
		} catch (Exception e) {
			log.error("deleteAllQuestionFromQuiz");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public ResponseQuizDto getQuizById(Integer quizId) {
		log.info("getQuizById");
		Optional<Quiz> quizById = quizRepo.findById(quizId);
		
		if(quizById.isPresent()) {
			ResponseQuizDto responseQuizDto = new ResponseQuizDto();
			BeanUtils.copyProperties(quizById.get(), responseQuizDto);
			System.out.println(responseQuizDto.toString());
			return responseQuizDto;
		}
		
		return null;
	}

	@Override
	public List<ResponseQuestionAnsDto> getAllQuestionByQuizId(Integer quizId) {
		log.info("getAllQuestionByQuizId QuizId ="+quizId);
				
		Optional<Quiz> quiz = quizRepo.findById(quizId);
		
		List<QuestionAnsOption> allQuestionsByQuizId = questionAnsRepo.getAllQuestionsByQuizId(quiz.get());
		List<ResponseQuestionAnsDto> responseQuestions = new ArrayList<>();
		
		for(QuestionAnsOption question : allQuestionsByQuizId) {
			ResponseQuestionAnsDto responseQuestion = new ResponseQuestionAnsDto();
			
			BeanUtils.copyProperties(question, responseQuestion);
			
			responseQuestions.add(responseQuestion);
			
		}
		
		return responseQuestions;
	}


}
