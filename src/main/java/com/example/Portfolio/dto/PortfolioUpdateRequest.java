package com.example.Portfolio.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class PortfolioUpdateRequest implements Serializable {
	
	
    
    @Size(min = 50, max = 200)
    private String self_introduction;
    
    private Long id;

}
