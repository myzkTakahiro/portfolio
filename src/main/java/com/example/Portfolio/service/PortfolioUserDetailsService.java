package com.example.Portfolio.service;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Portfolio.dao.PortfolioUserDetails;
import com.example.Portfolio.entity.users;
import com.example.Portfolio.repository.PortfolioRepository;

@Service
public class PortfolioUserDetailsService implements UserDetailsService{

  private final PortfolioRepository portfolioRepository;
  private final PasswordEncoder passwordEncoder;
 
 
  public PortfolioUserDetailsService(PortfolioRepository portfolioRepository, PasswordEncoder passwordEncoder) {
      this.portfolioRepository = portfolioRepository;
      this.passwordEncoder = passwordEncoder; // コンストラクタを通して注入
  }
  
  
  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	  
	  
	  
    // テスト用のハードコーディングされたユーザー情報
    if (email.equals("hoge")) {
        return User.builder()
                .username("hoge")
                // 注入されたエンコーダーでパスワードをハッシュ化
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
    }
    // DBベースのユーザー検索
    users appUser = portfolioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
    return new PortfolioUserDetails(appUser.getEmail(), appUser.getPassword(), authorities, appUser.getName(),appUser.getId(), appUser.getSelf_introduction());
    }
 
    
}
