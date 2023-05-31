package com.quizapp.entity;

import java.time.LocalDate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User_ID")
	private User user;

	@OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<QuestionAnsOption> questionAnsOption;
	
	public void addQuestions (List<QuestionAnsOption> questionAnsOptions) {
		this.questionAnsOption.addAll(questionAnsOptions);
	}
	
	public void addQuestion(QuestionAnsOption questionAnsOption) {
		this.questionAnsOption.add(questionAnsOption);
	}
	
	public void removeQuestions(List<QuestionAnsOption> questionAnsOptions) {
		this.questionAnsOption.remove(questionAnsOptions);
	}
	
	public void removeQuestion(QuestionAnsOption questionAnsOption) {
		this.questionAnsOption.remove(questionAnsOption);
	}
	
	
}
	

