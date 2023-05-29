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
import com.quizapp.repo.QuestionAndAnsRepo;

import jakarta.transaction.Transactional;

@Service
public class QuestionAnsOptionServiceImpl implements IQuestionAndAns {
	
	
	private static final Logger log = LoggerFactory.getLogger(QuestionAnsOptionServiceImpl.class);
	
	@Autowired
	private QuestionAndAnsRepo questionAndAnsRepo;

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

	@Override
	public boolean deleteQuestionByQuizId(Integer quizId) {
		log.info("deleteQuestionByQuizId");
		try {
			questionAndAnsRepo.deleteQuestionByQuizId(quizId);
			return true;
		} catch (Exception e) {
			log.info("deleteQuestionByQuizId");
			e.printStackTrace();
			return false;
		}
	}

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

	@Override
	public List<ResponseQuestionAnsDto> getAllQuestionsByQuizId(Integer quizId) {
		log.info("getAllQuestonsByQuizId");
		List<QuestionAnsOption> allQuestions = questionAndAnsRepo.getAllQuestionsByQuizId(quizId);
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

	@Override
	public boolean isQuestionPresent(Integer questionId) {
		log.info("isQuestionPresent");
		return questionAndAnsRepo.existsById(questionId);
	}


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
