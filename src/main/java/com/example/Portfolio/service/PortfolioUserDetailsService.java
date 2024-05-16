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

import com.example.Portfolio.entity.users;
import com.example.Portfolio.repository.PortfolioRepository;

@Service
public class PortfolioUserDetailsService implements UserDetailsService{

  private final PortfolioRepository portfolioRepository;
  private final PasswordEncoder passwordEncoder;
  
  @Autowired
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
    
    return User.builder()
            .username(appUser.getEmail()) // ユーザー名としてメールアドレスを使用
            .password(appUser.getPassword()) // ハッシュ化されたパスワード
            .roles("USER")
            .build();
    }
 
    
}
