package com.example.Portfolio.dto;

import java.io.Serializable;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class SkillNewAddRequest  implements Serializable {
	
	
	@NotEmpty(message = "項目名は必ず入力してください")
	@Size(max = 50, message = "項目名は50文字以内で入力してください")
	private String name;
	
	@NotNull
	@Min(1)
	private int study_time;
	
	
	private Long id;
	
	private Integer category_id;
	
	private Integer user_id;
	
}
