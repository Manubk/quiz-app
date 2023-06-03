package com.quizapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PARTICIPATE")
public class Participate extends Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PARTICIPATION_ID")
	private Integer participationId;
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "QUIZ_ID")
	private Quiz quiz;
	
	@Column(name = "TOTAL_POINTS")
	private Integer totalPoints;
	
	@Column(name = "GAINED_POINTS")
	private Integer gainedPoints;
	
	@Column(name = "NO_OF_ATTEMPTS")
	public Integer noOfAttempts = 0;
	
	
	private boolean result ;
}
