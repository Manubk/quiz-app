package com.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestQuizDto;
import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;
import com.quizapp.repo.QuizRepo;

@Service
public class QuizServiceImpl implements IQuizService {
	
	private static final Logger log = LoggerFactory.getLogger(QuizServiceImpl.class);
	
	@Autowired
	private QuizRepo quizRepo;
	
	@Override
	public boolean createQuiz(RequestQuizDto requestQuizDto) {
		log.info("createQuiz");
		Quiz quiz = new Quiz();
		BeanUtils.copyProperties(requestQuizDto, quiz);
		
		Quiz save = quizRepo.save(quiz);
		
		if(save.getQuizId() != null)
			return true;
		
		return false;
	}

	@Override
	public boolean deleteQuiz(Integer quizId) {
		log.info("deleteQuiz");
		try {
			quizRepo.deleteById(quizId);
			return true;
		} catch (Exception e) {
			log.error("deleteQuiz QuizID = "+quizId);
			e.printStackTrace();
			return false;
		}
		

	}

	@Override
	public boolean updateQuiz(RequestQuizDto requestQuizDto) {
		log.info("updateQuiz");
		Quiz quiz = new Quiz();
		
		BeanUtils.copyProperties(requestQuizDto, quiz);
		
		try {
			quizRepo.save(quiz);
			return true;
		} catch (Exception e) {
			log.error("updateQuiz QuizId = "+requestQuizDto.getQuizId());
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean addQuestionToQuiz(Integer quizId, QuestionAnsOption questionAnsOption) {
		log.info("addQuestionToQuiz");
		
		return false;
	}

	@Override
	public boolean isQuizPresent(Integer quizId) {
		log.info("isQuizPresent");
		return  quizRepo.existsById(quizId);
	}


}
