package com.quizapp.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizapp.entity.QuestionAnsOption;

@Repository
public interface QuestionAndAnsRepo extends JpaRepository<QuestionAnsOption, Integer>{
	
//	@Query("delete from QuestionAnsOption q where q.quizId =?1" )
	public boolean deleteQuestionByQuizId(Integer quizId);
	
//	@Query("select * from  QuestionAnsOption q where q.quizId = ?1")
	public List<QuestionAnsOption> getAllQuestionsByQuizId(Integer quizId);
}
