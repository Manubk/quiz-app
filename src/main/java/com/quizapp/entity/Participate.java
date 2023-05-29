package com.quizapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PARTICIPATE")
public class Participate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PARTICIPATION_ID")
	private Integer participationId;
	
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "QUIZ_ID")
	private Integer quizId;
	
	@Column(name = "TOTAL_POINTS")
	private Integer totalPoints;
	
	@Column(name = "GAINED_POINTS")
	private Integer gainedPoints;
	
	private boolean result ;
}
