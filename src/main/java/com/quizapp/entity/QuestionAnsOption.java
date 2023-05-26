package com.quizapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="QUESTION_ANS_OPT")
@Data
public class QuestionAnsOption extends Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTION_ID")
	private Integer questionId;
	
	@Column(name = "QUESTION")
	private String question;
	
	@Column(name = "ANSWER")
	private String answer;
	
	@Column(name = "OPTION_1")
	private String option1;
	
	@Column(name = "OPTION_2")
	private String option2;
	
	@Column(name = "OPTION_3")
	private String option3;
	
	@Column(name = "OPTION_4")
	private String option4;
}
