package com.example.Portfolio.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Portfolio.repository.PortfolioRepository;
import com.example.Portfolio.entity.Portfolio;


@Service
public class PortfolioDetailsService implements UserDetailsService{

    
    @Autowired
    private PortfolioRepository userDao;

    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Portfolio user = userDao.findUser(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User" + userName + "was not found in the database");
        }
       

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");

        grantList.add(authority);

       
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

       
        UserDetails userDetails = (UserDetails)new User(user.getName(), encoder.encode(user.getPassword()),grantList);

        return userDetails;
    }

}


