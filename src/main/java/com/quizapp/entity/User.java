package com.quizapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class User extends Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "USER_EMAIL")
	private String userEmail;
	
	@Column(name = "USER_PASS")
	private String userPass;
	
	@Column(name = "NO_OF_QUIZ_PATICIPATED")
	private Integer noOfQuizParticipated = 0;
	
	@Column(name = "NO_OF_QUIZ_CREATED")
	private Integer noOfQuizCreate = 0;
	
	@Column(name = "TOTAL_SCORE")
	private Integer totalScore = 0;
	
	@Column(name = "TOTAL_GAINED")
	private Integer totalGainer = 0;
	
	@Column(name = "DELETED")
	private boolean isDeleted = false;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy ="user" )
	private List<Quiz> quizs;
	
	@Override
	public String toString() {
		return "USER =[userId="+this.userId+",userName="+this.userName+",userEmail="+this.userEmail+
				",userPass="+this.userPass+",noOfQuizParticipated="+this.noOfQuizParticipated+
				",noOfQuizCreate="+this.noOfQuizCreate+",totalScore="+this.totalScore+",totalGainer"
				+this.totalGainer+",isDeleted="+this.isDeleted+"]";
	}
	
	
}
