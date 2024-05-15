package com.example.Portfolio.dao;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Portfolio.entity.Portfolio;






public class PortfolioUserDetails extends Portfolio {

	

	// username,password,authoritiesを引数に取るコンストラクタを用意
	public PortfolioUserDetails(String eamil, String password, Collection<? extends GrantedAuthority> authorities) {
		super(email, password, authorities);
	}
	
}