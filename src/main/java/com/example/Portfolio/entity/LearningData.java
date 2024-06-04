package com.example.Portfolio.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;


@Data
@Table(name="learning_data")
public class LearningData implements Serializable {
	
	private Long id;
	
	private String name;
	
	 @Column(value="category_id")
	 private Integer categoryId;
	 
	 @Column(value="study_time")
	 private int studyTime;
	 
	 @Column(value="user_id")
	 private Integer userId;
	 
	 private Instant month;
	
}
