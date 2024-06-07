package com.example.Portfolio.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class SkillTimeUpdateRequest implements Serializable {
	
	
	private int study_time;
	
	public int getStudyTime() {
		return study_time;
	}
	
	public void setStudyTime(int study_time) { 
		this.study_time = study_time;
	}
	
	private String name;
	
	private Long id;
	
	private Integer category_id;
	
	private Integer user_id;

}
