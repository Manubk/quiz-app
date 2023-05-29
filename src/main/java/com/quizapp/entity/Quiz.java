package com.quizapp.entity;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "QUIZS")
@Data
@NoArgsConstructor
public class Quiz extends Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUIZ_ID")
	private Integer quizId;
	
	@Column(name = "QUIZ_NAME")
	private String quizName;
	
	@Column(name = "QUIZ_OWNER_NAME")
	private Integer quizOwnerId;
	
	@Column(name = "TOPPER")
	private Integer topperId;
	
	@Column(name = "TOTAL_PARTICIPENTS")
	private Integer totalParticipents;
	
	@OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<QuestionAnsOption> questionAnsOption;
	
	private void addQuestions (List<QuestionAnsOption> questionAnsOptions) {
		this.questionAnsOption.addAll(questionAnsOptions);
	}
	
	private void addQuestion(QuestionAnsOption questionAnsOption) {
		this.questionAnsOption.add(questionAnsOption);
	}
	
	private void removeQuestions(List<QuestionAnsOption> questionAnsOptions) {
		this.questionAnsOption.remove(questionAnsOptions);
	}
	
	private void removeQuestion(QuestionAnsOption questionAnsOption) {
		this.questionAnsOption.remove(questionAnsOption);
	}
	
	
}
	

