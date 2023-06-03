package com.quizapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.entity.Quiz;
import com.quizapp.entity.User;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer>{
	
//	 public List<Quiz> findByQuizOwnerId(Integer quizOwnerId);
	 
	 List<Quiz> findByUser(User user);
}
