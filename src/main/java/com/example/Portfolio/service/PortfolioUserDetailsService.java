package com.example.Portfolio.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Portfolio.entity.Portfolio;
import com.example.Portfolio.repository.PortfolioRepository;

public class PortfolioUserDetailsService implements UserDetailsService {
	
	private final PortfolioRepository portfolioRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public PortfolioUserDetailsService(PortfolioRepository portfolioRepository, PasswordEncoder passwordEncoder) {
		this.portfolioRepository  = portfolioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		if (email.equals("hoge")) {
	        return User.builder()
	                .username("hoge")
	                .password(passwordEncoder.encode("password"))
	                .roles("USER")
	                .build();
	    }
		
		Portfolio portfolio = portfolioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email:" + email));
		
		return User.builder()
				.username(portfolio.getEmail())
				.password(portfolio.getPassword())
				.roles("USER")
				.build();
	}
	
	public void saveUser(Portfolio user) {
		portfolioRepository.save(user);
	}
	
	public List<Portfolio> getAllUsers() {
        Iterable<Portfolio> users = portfolioRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                         .collect(Collectors.toList());
    }

}
