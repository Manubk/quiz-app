package com.quizapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizapp.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("from User where userEmail = :email and userPass = :pass")
	public User findUserByEmailAndPass(String email, String pass);
	
	
	
}
