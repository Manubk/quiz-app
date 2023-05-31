package com.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestParticipateDto;
import com.quizapp.dto.responsedto.ResponseParticipateDto;
import com.quizapp.entity.Participate;
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
	private QuizRepo quizRepo;

	@Override
	public boolean savePaticipation(RequestParticipateDto requestParticipateDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateParticipation(RequestParticipateDto requestParticipateDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer participateId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Participate findById(Integer participateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isParticipatePresent(Integer participateId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ResponseParticipateDto> findAllParticipationByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResponseParticipateDto> findAllParticipationByQuiz(Integer quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
