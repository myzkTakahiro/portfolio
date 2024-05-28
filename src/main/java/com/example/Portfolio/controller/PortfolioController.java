package com.example.Portfolio.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.Portfolio.dao.PortfolioUserDetails;
import com.example.Portfolio.dto.PortfolioAddRequest;
import com.example.Portfolio.dto.PortfolioSearchRequest;
import com.example.Portfolio.dto.PortfolioUpdateRequest;
import com.example.Portfolio.dto.SkilleditRequest;
import com.example.Portfolio.entity.LearningData;
import com.example.Portfolio.entity.users;
import com.example.Portfolio.service.LearningDataService;
import com.example.Portfolio.service.PortfolioService;
import com.example.Portfolio.service.PortfolioUserDetailsService;

@RequestMapping("/")
@Controller
public class PortfolioController {
	
	@Autowired
    private PortfolioService portfolioService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private LearningDataService learningdataService;
	
	@GetMapping(value = "/add")
	    public String displayAdd(Model model) {
	        model.addAttribute("portfolioAddRequest", new PortfolioAddRequest());
	        return "/user/add";
	    }
	
	 @GetMapping(value = "/top")
	 	public String displayTop(Authentication loginUser,Model model) {
		 model.addAttribute("portfolioAddRequest", new PortfolioAddRequest());
	        model.addAttribute("email", loginUser.getName());
		 PortfolioUserDetails userDetails = (PortfolioUserDetails) loginUser.getPrincipal();
		 	model.addAttribute("portfolioAddRequest", new PortfolioAddRequest());
	        model.addAttribute("userName", userDetails.getName());
	        model.addAttribute("selfintroduction", userDetails.getSelfIntroduction());
		 return "user/top";
	 }
	 
	 @GetMapping(value = "/profile")
	 	public String displayPro(Authentication loginUser, Model model) {
		 PortfolioUserDetails userDetails = (PortfolioUserDetails) loginUser.getPrincipal();
		 model.addAttribute("portfolioUpdateRequest", new PortfolioUpdateRequest());
		 model.addAttribute("id", userDetails.getId());
		 return "user/profile";
	 }
	 
	 @GetMapping("/skilledit")
		 public String displayList(Model model) {
		        List<LearningData> userList = learningdataService.Allfind();
		        model.addAttribute("userlist", userList);
		        model.addAttribute("userSearchRequest", new SkilleditRequest());
		 return "user/skilledit";
	 }
	 
	 @GetMapping(value="/skillnew")
	 	public String displayNew(Model model) {
		 return "user/skillnew";
	 }
	 

	 @RequestMapping("/login")
	    public String search(@ModelAttribute PortfolioSearchRequest portfolioSearchRequest, Model model) {
		 
	        return "user/login";
	    }

	 
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String create(@Validated @ModelAttribute PortfolioAddRequest portfolioRequest, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            // 入力チェックエラーの場合
	            List<String> errorList = new ArrayList<String>();
	            for (ObjectError error : result.getAllErrors()) {
	                errorList.add(error.getDefaultMessage());
	            }
	            model.addAttribute("validationError", errorList);
	            return "user/add";
	        }
	        // ユーザー情報の登録
	        portfolioService.save(portfolioRequest);
	        return "redirect:/login";
	    }
	 
	 @RequestMapping(value = "/profile", method = RequestMethod.POST)
	    public String update(@Validated @ModelAttribute PortfolioUpdateRequest portfolioUpdateRequest, BindingResult result, Authentication loginUser, Model model, Authentication authentication) {
	        if (result.hasErrors()) {
	            List<String> errorList = new ArrayList<String>();
	            for (ObjectError error : result.getAllErrors()) {
	                errorList.add(error.getDefaultMessage());
	            }
	            model.addAttribute("validationError", errorList);
	            PortfolioUserDetails userDetails = (PortfolioUserDetails) loginUser.getPrincipal();
	            model.addAttribute("id", userDetails.getId());
	            return "user/profile";
	        }
	        // ユーザー情報の更新
	        portfolioService.update(portfolioUpdateRequest);
	        
			
			PortfolioUserDetails updatedUserDetails = (PortfolioUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
	         
	         //セキュリティコンテキストを更新
	         UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                 updatedUserDetails, authentication.getCredentials(), updatedUserDetails.getAuthorities());
	         SecurityContextHolder.getContext().setAuthentication(authToken);
	        
	        return "redirect:/top";
	    }

	 


}