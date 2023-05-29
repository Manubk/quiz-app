package com.quizapp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.entity.Participate;
import com.quizapp.repo.ParticipateRepo;

@Service
public class ParticipateServiceImpl implements IParticipateService {

	private static final Logger log = LoggerFactory.getLogger(ParticipateServiceImpl.class);

	@Autowired
	private ParticipateRepo participateRepo;

	@Override
	public boolean save(Participate participate) {
		log.info("saveParticipate");

		Participate save = participateRepo.save(participate);

		if (save.getParticipationId() != null)
			return true;

		return false;
	}

	@Override
	public boolean update(Participate participate) {
		log.info("updateParticipate");

		try {
			Participate save = participateRepo.save(participate);
			return true;
		} catch (Exception e) {
			log.error("exception in updateParticipate");
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteById(Integer participateId) {
		log.info("DeleteById");

		try {
			participateRepo.deleteById(participateId);
			return true;
		} catch (Exception e) {
			log.error("exception in deleteById");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Participate findById(Integer participateId) {
		log.info("findParticipateById");
		
		Optional<Participate> findById = participateRepo.findById(participateId);
		
		return findById.get();
	}

	@Override
	public boolean isParticipatePresent(Integer participateId) {
		log.info("isPresent");
		return participateRepo.existsById(participateId);
	
	}

}
