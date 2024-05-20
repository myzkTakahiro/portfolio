package com.example.Portfolio.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PortfolioUpdateRequest implements Serializable {
	
	
    @NotEmpty
    @Size(min = 50, max = 200, message = "自己紹介は50文字以上200文字以下で入力してください")
    private String self_introduction;
    
    private Long id;

}
