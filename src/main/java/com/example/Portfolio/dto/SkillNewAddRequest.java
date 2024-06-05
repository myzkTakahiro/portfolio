package com.example.Portfolio.dto;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class SkillNewAddRequest  implements Serializable {
	
	
	@NotEmpty(message = "項目名は必ず入力してください") 
	@Size(max = 50, message = "項目名は50文字以内で入力してください")
	private String name;
	
	@NotNull(message = "学習時間は必ず入力してください")
	@Positive(message = "学習時間は1以上の数字で入力してください")
	private int study_time;
	
	
	private Long id;
	
	
	private Integer category_id;
	
	private Integer user_id;
	
	
	private Instant month;
}
