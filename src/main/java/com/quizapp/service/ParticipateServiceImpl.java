package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestParticipateDto;
import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseParticipateDto;
import com.quizapp.dto.responsedto.ResponseUserDto;
import com.quizapp.entity.Participate;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;
import com.quizapp.repo.ParticipateRepo;
import com.quizapp.repo.QuizRepo;
import com.quizapp.repo.UserRepo;

@Service
public class ParticipateServiceImpl implements IParticipateService {

	private static final Logger log = LoggerFactory.getLogger(ParticipateServiceImpl.class);

	@Autowired
	private ParticipateRepo participateRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private IUserService userService;

	@Autowired
	private IQuizService quizService;

	@Override
	public boolean savePaticipation(RequestParticipateDto requestParticipateDto) {
		log.info("saveParticipation");
		
		int gainedPoint = 0;

		for (RequestQuestionAnsDto requestQuestionDto : requestParticipateDto.getRequestQuestionAns()) {
			if (requestQuestionDto.getSubmitedAns().strip().equalsIgnoreCase(requestQuestionDto.getAnswer().strip())) {
				gainedPoint++;
			}
		}
		
		User user = userService.findInUserById(requestParticipateDto.getUserId());
		
		Quiz quiz = quizService.getInQuizById(requestParticipateDto.getQuizId());
		quiz.setTotalParticipents(quiz.getTotalParticipents() + 1);

		Participate participate = new Participate();
		participate.setTotalPoints(requestParticipateDto.getRequestQuestionAns().size());
		participate.setGainedPoints(gainedPoint);
		participate.setUser(user);
		participate.setQuiz(quiz);
		participate.setNoOfAttempts(participate.getNoOfAttempts()+1);
		
		Participate participateSaved = participateRepo.save(participate);
		
		user.setNoOfQuizParticipated(user.getNoOfQuizParticipated() + 1);
		user.setTotalScore(user.getTotalScore()+participateSaved.getTotalPoints());
		user.setTotalGainer(user.getTotalGainer() + gainedPoint);
		
		if(participateSaved.getParticipationId() != null) {
			quizService.updateQuizByParticipate(participate);
			userService.saveUser(user);
		}

		return false;
	}

	@Override
	public boolean updateParticipation(RequestParticipateDto requestParticipateDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer participateId) {
		log.info("deleteById participateId = "+participateId);
		
		try {
			participateRepo.deleteById(participateId);
			return true;
		} catch (Exception e) {
			log.error("deleteById participateId = "+participateId);
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public ResponseParticipateDto findById(Integer participateId) {
		log.info("findById participationId = "+participateId);
		
		Optional<Participate> participate = participateRepo.findById(participateId);
		ResponseParticipateDto responseDto = new ResponseParticipateDto();
		try {
		BeanUtils.copyProperties(participate.get(), responseDto);
		return responseDto;
		} catch (Exception e) {
			log.error("findById participationId = "+participateId);
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public boolean isParticipatePresent(Integer participateId) {
		log.info("isParticipatePresent participateId = "+participateId);
		
		return participateRepo.existsById(participateId);
		
	}

	@Override
	public List<ResponseParticipateDto> findAllParticipationByUser(Integer userId) {
		log.info("findAllParticipationByUser UserId = "+userId);
		
		User user = userService.findInUserById(userId);
		
		List<Participate> participations = participateRepo.findByUser(user);
		List<ResponseParticipateDto> responseParticipationDtos = new ArrayList<>();
		
		for(Participate participate : participations) {
			ResponseParticipateDto responseParticipateDto = new ResponseParticipateDto();
			
			BeanUtils.copyProperties(participate, responseParticipateDto);
			
			responseParticipationDtos.add(responseParticipateDto);
			
		}
	
		return responseParticipationDtos;
	}

	@Override
	public List<ResponseParticipateDto> findAllParticipationByQuiz(Integer quizId) {
log.info("findAllParticipationByQuiz QuizId = "+quizId);
		
		Quiz quiz = quizService.getInQuizById(quizId);	
		
		List<Participate> participations = participateRepo.findByQuiz(quiz);
		List<ResponseParticipateDto> responseParticipationDtos = new ArrayList<>();
		
		for(Participate participate : participations) {
			ResponseParticipateDto responseParticipateDto = new ResponseParticipateDto();
			
			BeanUtils.copyProperties(participate, responseParticipateDto);
			
			responseParticipationDtos.add(responseParticipateDto);
			
		}
	
		return responseParticipationDtos;
	}

	@Override
	public boolean deleteByUserId(Integer userId) {
		log.info("deleteByUser userId = "+userId);
		
		try {
			User user = userService.findInUserById(userId);
			
			participateRepo.deleteByUser(user);
			
			return true;
		} catch (Exception e) {
			log.error("deleteByUser userId = "+userId);
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Participate findByQuizAndUser(User user, Quiz quiz) {
		log.info("findBYQuizAndUser UserId = "+user.getUserId()+" QuizId = "+quiz.getQuizId());
		
		Participate participate = participateRepo.findByQuizAndUser(user, quiz);
		
		
		return participate;
	}

	@Override
	public boolean savePaticipation(RequestParticipateDto requestParticipateDto, Integer participentId,
			Integer quizId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByQuizId(Integer quizId) {
		log.info("deleteByQuizId quizId = "+quizId);
		
		Quiz quiz = quizService.getInQuizById(quizId);
		
		try {
			participateRepo.deleteByQuiz(quiz);
			return true;
		} catch (Exception e) {
			log.error("deleteByQuizId quizId = "+quizId);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ResponseParticipateDto> getAllParticipates() {
		log.info("getAllParticipates");
		List<Participate> participates = participateRepo.findAll();
		
		List<ResponseParticipateDto> responseDtos = new ArrayList<>();
		
		for(Participate participate : participates ) {
			ResponseParticipateDto responseDto = new ResponseParticipateDto();
			
			BeanUtils.copyProperties(participate, responseDto);
			responseDtos.add(responseDto);
		}
		
		return responseDtos;
	}

}
