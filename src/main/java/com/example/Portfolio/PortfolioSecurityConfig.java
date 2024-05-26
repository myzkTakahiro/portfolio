package com.example.Portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class PortfolioSecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                // アクセス制限をかけない
                .requestMatchers("/"
                		,"/add"
                		, "/login"
                		, "/css/**"
                        , "/login?error"
                        , "/skilledit"
                        , "/mysql-console/**")
                .permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((login) -> login
                .usernameParameter("username")
                .passwordParameter("password")
                // ログインを実行するページ
                .loginProcessingUrl("/login")
                // ログイン画面
                .loginPage("/login")
                // ログイン失敗時のURL
                .failureUrl("/login?error")
                // ログインに成功した場合の遷移先
                .defaultSuccessUrl("/top", true)
                // アクセス権
                .permitAll()

            )
            .logout((logout) -> logout
            		
            	.logoutSuccessUrl("/login?logout")
                 // ログアウトした場合の遷移先
                .permitAll());
       
        
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    


}
