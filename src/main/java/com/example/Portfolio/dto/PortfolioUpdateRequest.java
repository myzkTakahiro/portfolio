package com.example.Portfolio.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PortfolioUpdateRequest implements Serializable {
	
	
    @NotEmpty(message="")
    @Size(min = 50, max = 200, message = "自己紹介は50文字以上200文字以下で入力してください")
    private String self_introduction;
    
    public String getSelfIntroduction() {
    	return self_introduction;
    }
    
    public void setSelfIntroduction(String self_introduction) {
    	this.self_introduction = self_introduction;
    }
    
    private Long id;
    
    private String name;
    
    private String email;
    
    private String password;
    
    private Integer user_id;

}
