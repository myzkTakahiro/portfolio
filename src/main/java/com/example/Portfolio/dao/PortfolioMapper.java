package com.example.Portfolio.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Portfolio.dto.PortfolioAddRequest;

import com.example.Portfolio.dto.PortfolioUpdateRequest;
import com.example.Portfolio.entity.Portfolio;



@Mapper
public interface PortfolioMapper {
	
	
	
	List<Portfolio> findAll();
	
	Portfolio findById(Long id);
	
	
	void save(PortfolioAddRequest portfolioRequest);
	
	
	void update(PortfolioUpdateRequest portfolioUpdateRequest);
	
	
	public Portfolio getAccount(String email);

}


