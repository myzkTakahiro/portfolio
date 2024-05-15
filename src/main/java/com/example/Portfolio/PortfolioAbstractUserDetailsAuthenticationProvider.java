//package com.example.Portfolio;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.example.Portfolio.dao.PortfolioUserDetails;
//import com.example.Portfolio.entity.Portfolio;
//import com.example.Portfolio.repository.PortfolioRepository;

//public class PortfolioAbstractUserDetailsAuthenticationProvider 
//extends AbstractUserDetailsAuthenticationProvider{
	
	

	//@Autowired
	//private PortfolioRepository portfolioRepository;
	
    
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;

    
	//@Override
	//protected void additionalAuthenticationChecks(UserDetails userDetails,
			//UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {}

	//@Override
	//protected UserDetails retrieveUser(String email, UsernamePasswordAuthenticationToken authentication)
			//throws AuthenticationException {
		//String password = (String) authentication.getCredentials(); // authenticationからpasswordを取得
		//Portfolio id = portfolioRepository.findByEmail(String email); // usernameでDBの検索を行う。
		//if(bCryptPasswordEncoder.matches(password, getPassword())) { // 入力されたパスワードとDBにあったパスワードが一致するか判定
			//return new UserDetails(email); // 一致したらUserDetailsをnewしてreturn
		//}else {
			//throw new UsernameNotFoundException("user not found"); // 一致しなかったらUsernameNotFoundExceptionをスロー
		//}
		
	//}

//}
