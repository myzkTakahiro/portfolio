package com.example.Portfolio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Portfolio.dao.PortfolioMapper;
import com.example.Portfolio.dto.PortfolioAddRequest;






@Service
public class PortfolioService {
	
	@Autowired
	private PortfolioMapper portfolioMapper;
	
	
	
	public void save(PortfolioAddRequest portfolioAddRequest) {
        portfolioMapper.save(portfolioAddRequest);
    }

}
