package com.quizapp.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Auditable {
	
	@Column(name = "CREATED_AT" ,updatable = false)
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
