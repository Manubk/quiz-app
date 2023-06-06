package com.quizapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="QUESTION_ANS_OPT")
@Data
public class QuestionAnsOption extends Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTION_ID")
	private Integer questionId;
	

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "QUIZ_Id")
	private Quiz quiz;
	
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
	
	public String toString() {
		return "Question ["+this.question+","+this.answer+","+this.option1+","
				+this.option2 +","+this.option3+","+this.option4+","+this.quiz.getQuizId()
				+","+this.quiz.getQuizName()+"]";
	}
}
