package com.quizapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.entity.QuestionAnsOption;
import com.quizapp.entity.User;
import com.quizapp.service.IQuestionAndAns;

@RestController
public class QuestionAndOptionController {
	
	
	private static final Logger log = LoggerFactory.getLogger(QuestionAndOptionController.class);


	
}
