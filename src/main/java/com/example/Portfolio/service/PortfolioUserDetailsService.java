package com.example.Portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Portfolio.entity.Portfolio;
import com.example.Portfolio.dao.PortfolioMapper;
import com.example.Portfolio.dao.PortfolioUserDetails;

@Service
public class PortfolioUserDetailsService implements UserDetailsService {
	
	 

	    @Autowired
	   PortfolioMapper portfolioMapper;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        //入力された名前をキーにemployeeテーブルのレコードを1件取得
	        Portfolio portfolio = portfolioMapper.findByEmail(email);

	        //該当レコードが取得できなかった場合はエラーにする
	        if  (portfolio   ==  null)   {
	            throw new UsernameNotFoundException("Wrong email or password");
	        }

	        //ログインユーザー権限を設定
	        String role = "ROLE_ADMIN";

	        return new PortfolioUserDetails(portfolio, role);
	    }

}
