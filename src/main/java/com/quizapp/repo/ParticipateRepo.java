package com.quizapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.entity.Participate;

@Repository
public interface ParticipateRepo extends JpaRepository<Participate, Integer> {

}
