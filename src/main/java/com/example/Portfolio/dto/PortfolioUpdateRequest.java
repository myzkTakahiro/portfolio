package com.example.Portfolio.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PortfolioUpdateRequest extends PortfolioAddRequest implements Serializable {
	
	@NotNull
    private Long id;

}
