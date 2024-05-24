package com.example.Portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;


@Data
@Table(name="learning_data")
public class LearningData implements Serializable {
	
	private Long id;
	
	private String name;
	
	private Long category_id;
}
