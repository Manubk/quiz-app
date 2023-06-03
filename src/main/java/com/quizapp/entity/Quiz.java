package com.quizapp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	
	@JoinColumn(name = "TOPPER_ID")
	@OneToOne
	private User quizTopper;
	
	@Column(name = "TOTAL_PARTICIPENTS")
	private Integer totalParticipents = 0;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User_ID")
	private User user;
	
	@OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<QuestionAnsOption> questionAnsOption;
	
	@Column(name = "LAST_DATE")
	private LocalDateTime lastDate;
	
	@Column(name = "TOP_SCORE")
	private Integer topScore = 0;
	
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
	
	@Override
	public String toString() {
		return "QUIZ =[ quizId="+this.quizId+",quizName="+this.quizName+((this.quizTopper != null)?
				",quizTopper="+this.quizTopper.getUserId():"")+",totalParticipents="+
				this.totalParticipents+",user="+this.user.getUserId()+"lastDate="
				+this.lastDate+",topScore="+this.topScore+"]";
	}
}
	

