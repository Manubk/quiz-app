package com.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quizapp.dto.requestdto.RequestParticipateDto;
import com.quizapp.dto.responsedto.ResponseParticipateDto;
import com.quizapp.entity.Participate;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;

@Service
public interface IParticipateService {
	
	/*
	 * Creating the new Participation Data
	 */
	public boolean savePaticipation(RequestParticipateDto requestParticipateDto);
	
	/*
	 * Updating the Participation data early used
	 */
	public boolean updateParticipation(RequestParticipateDto requestParticipateDto);
	
	/*
	 * Deleting Participation By Id
	 */
	public boolean deleteById(Integer participateId);
	
	/*
	 * Deleting the participation data by userId
	 */
	public boolean deleteByUserId(Integer userId);
	/*
	 * Getting the Participation By Id
	 */
	public Participate findById(Integer participateId);

	/*
	 * Finding is Participation is present
	 */
	public boolean isParticipatePresent(Integer participateId);
	
	/*
	 * Getting the Paricipation By User
	 */
	public List<ResponseParticipateDto> findAllParticipationByUser(Integer userId);
	
	/*
	 * Getting the Paricipation By Quiz
	 */
	public List<ResponseParticipateDto> findAllParticipationByQuiz(Integer quizId);
	
	public Participate findByQuizAndUser(User user,Quiz quiz);
}
