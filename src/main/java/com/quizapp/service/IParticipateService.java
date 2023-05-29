package com.quizapp.service;

import org.springframework.stereotype.Service;

import com.quizapp.entity.Participate;

@Service
public interface IParticipateService {
	public boolean save(Participate participate);
	public boolean update(Participate participate);
	public boolean deleteById(Integer participateId);
	public Participate findById(Integer participateId);
	public boolean isParticipatePresent(Integer participateId);
}
