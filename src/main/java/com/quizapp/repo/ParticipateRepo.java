package com.quizapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizapp.entity.Participate;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;

@Repository
public interface ParticipateRepo extends JpaRepository<Participate, Integer> {
	
	@Query("from Participate where user =:user AND quiz =:quiz ")
	public Participate findByQuizAndUser(User user,Quiz quiz);

	List<Participate> findByQuiz(Quiz quiz);
	
	List<Participate> findByUser(User user);
	
	@Query("delete from Participate where user =:user")
	public void deleteByUser(User user);
	
	
	@Query("delete from Participate where quiz =:quiz")
	public void deleteByQuiz(Quiz quiz);
}
