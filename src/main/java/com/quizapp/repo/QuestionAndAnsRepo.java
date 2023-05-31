package com.quizapp.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.Quiz;

import jakarta.transaction.Transactional;

@Repository
public interface QuestionAndAnsRepo extends JpaRepository<QuestionAnsOption, Integer>{
	
	@Modifying
	@Transactional
	@Query("delete from QuestionAnsOption  where quiz =:quiz")
	public boolean deleteQuestionByQuizId(Quiz quiz);

	@Query("from  QuestionAnsOption  where quiz = :quiz")
	public List<QuestionAnsOption> getAllQuestionsByQuizId(Quiz quiz);
	

}
