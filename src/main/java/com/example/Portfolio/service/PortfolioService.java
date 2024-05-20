package com.example.Portfolio.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Portfolio.dao.PortfolioMapper;
import com.example.Portfolio.dto.PortfolioAddRequest;
import com.example.Portfolio.dto.PortfolioUpdateRequest;







@Service
public class PortfolioService {
	
	
	
	
	@Autowired
	private PortfolioMapper portfolioMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	public void save(PortfolioAddRequest portfolioAddRequest) {
		String encodedPassword = passwordEncoder.encode(portfolioAddRequest.getPassword());
        portfolioAddRequest.setPassword(encodedPassword);
        portfolioMapper.save(portfolioAddRequest);
    }



	public void update(PortfolioUpdateRequest portfolioUpdateRequest) {
		
		PortfolioMapper.update(portfolioUpdateRequest);
	}


}
