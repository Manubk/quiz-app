package com.quizapp.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "QUIZS")
@Data
public class Quiz extends Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUIZ_ID")
	private Integer quizId;
	
	@Column(name = "QUIZ_NAME")
	private String quizName;
	
	@Column(name = "QUIZ_OWNER_NAME")
	private Integer quizOwnerId;
	
	@Column(name = "CREATED_AT",updatable = false)
	@CreationTimestamp
	private LocalDate createdAt;
	
	@Column(name = "UPDATED_AT",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedAt;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
}
