package com.example.Portfolio.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.Portfolio.dao.PortfolioUserDetails;
import com.example.Portfolio.dto.PortfolioAddRequest;
import com.example.Portfolio.dto.PortfolioSearchRequest;
import com.example.Portfolio.dto.PortfolioUpdateRequest;
import com.example.Portfolio.dto.SkillNewAddRequest;
import com.example.Portfolio.dto.SkillTimeUpdateRequest;
import com.example.Portfolio.dto.SkilleditRequest;
import com.example.Portfolio.entity.Categories;
import com.example.Portfolio.entity.LearningData;
import com.example.Portfolio.entity.StudyTime;
import com.example.Portfolio.entity.users;
import com.example.Portfolio.service.LearningDataService;
import com.example.Portfolio.service.PortfolioService;
import com.example.Portfolio.service.PortfolioUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	        List<StudyTime> totalTime = learningdataService.sumTime();
	        model.addAttribute("totalTime", totalTime);
	        System.out.println(totalTime);
		 return "user/top";
	 }
	 
	 @GetMapping(value = "/profile")
	 	public String displayPro(Authentication loginUser, Model model) {
		 PortfolioUserDetails userDetails = (PortfolioUserDetails) loginUser.getPrincipal();
		 model.addAttribute("portfolioUpdateRequest", new PortfolioUpdateRequest());
		 model.addAttribute("id", userDetails.getId());
		 model.addAttribute("selfintroduction", userDetails.getSelfIntroduction());
		 return "user/profile";
	 }
	 
	 @GetMapping("/skilledit")
		 public String displayList(Authentication loginUser, Model model) {
		        List<LearningData> userList = learningdataService.Allfind();
		        model.addAttribute("userlist", userList);
		        model.addAttribute("skillTimeUpdateRequest", new SkillTimeUpdateRequest());
		        PortfolioUserDetails userDetails = (PortfolioUserDetails) loginUser.getPrincipal();
		        model.addAttribute("id", userDetails.getId());
		 return "user/skilledit";
	 }
	 
	 @GetMapping(value="/skillnew")
	 	public String displayNew(@RequestParam Integer category_id,  Authentication loginUser, Model model) {
		 model.addAttribute("category_id", category_id);
		 PortfolioUserDetails userDetails = (PortfolioUserDetails) loginUser.getPrincipal();
		 model.addAttribute("user_id", userDetails.getId());
		 model.addAttribute("skillNewAddRequest", new SkillNewAddRequest());
		 Categories categoryName = learningdataService.findCategory(category_id);
	        model.addAttribute("category_name", categoryName);
		 return "user/skillnew";
	 }
	 
	  @GetMapping("/user/{id}/delete")
	    public String delete(@PathVariable Long id, Model model) {
	        // ユーザー情報の削除
	        learningdataService.delete(id);
	        return "redirect:/skilledit";
	    }
	 

	 @RequestMapping("/login")
	    public String search(@ModelAttribute PortfolioSearchRequest portfolioSearchRequest, Model model) {
		 
	        return "user/login";
	    }

	 
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String create(@Validated @ModelAttribute PortfolioAddRequest portfolioRequest, BindingResult result, Model model,HttpServletRequest request) {
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
	        
	        try {
	            // ユーザー情報をロード
	            UserDetails 
	            userDetails = userDetailsService.loadUserByUsername(portfolioRequest.getEmail());

	            // ロードしたユーザー情報をログに出力
	            System.out.println("User details: " + userDetails);

	            // 認証トークン（ユーザー名、パスワード、およびユーザーの権限情報を保持）の作成
	            UsernamePasswordAuthenticationToken authToken = 
	                new UsernamePasswordAuthenticationToken(userDetails, portfolioRequest.getPassword(), userDetails.getAuthorities());

	            // 作成した認証トークンをログに出力
	            System.out.println("Authentication token: " + authToken);

	            // セキュリティコンテキストに認証情報を設定
	            SecurityContextHolder.getContext().setAuthentication(authToken);

	            // 認証情報をセッションに保存
	            HttpSession session = request.getSession(true);
	            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

	        } catch (Exception e) {
	            // エラーメッセージをログに出力
	            System.out.println("Login error: " + e.getMessage());
	            model.addAttribute("loginError", "ログインに失敗しました: " + e.getMessage());
	            return "user/add";
	        }


	        return "redirect:/top";
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
	            model.addAttribute("selfintroduction", userDetails.getSelfIntroduction());
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
	 
	 
	 @RequestMapping(value = "/skillnew", method = RequestMethod.POST)
	    public String skilladd(@RequestParam Integer category_id,@Validated @ModelAttribute SkillNewAddRequest skillNewAddRequest, BindingResult result, Authentication loginUser, Model model) {
	        if (result.hasErrors()) {
	            // 入力チェックエラーの場合
	            List<String> errorList = new ArrayList<String>();
	            for (ObjectError error : result.getAllErrors()) {
	                errorList.add(error.getDefaultMessage());
	            }
	            if (learningdataService.isItemExist(skillNewAddRequest.getName())) {
	                result.rejectValue("name", "duplicate", "入力した項目名は既に使用されています");
	            }
	            
	            model.addAttribute("validationError", errorList);
	            PortfolioUserDetails userDetails = (PortfolioUserDetails) loginUser.getPrincipal();
	            model.addAttribute("user_id", userDetails.getId());
	            model.addAttribute("portfolioAddRequest", new PortfolioAddRequest());
	            model.addAttribute("category_id", category_id);
	            Categories categoryName = learningdataService.findCategory(category_id);
		        model.addAttribute("category_name", categoryName);
	            return "user/skillnew";
	        }
	        // ユーザー情報の登録
	        learningdataService.add(skillNewAddRequest);
	        return "redirect:/skilledit";
	    }
	 
	 
	 @RequestMapping(value = "/skilledit", method = RequestMethod.POST)
	    public String timeUpdate(@Validated @ModelAttribute SkillTimeUpdateRequest skillTimeUpdateRequest, BindingResult result, Authentication loginUser, Model model, Authentication authentication) {
	        // ユーザー情報の更新
		 	
	        learningdataService.timeUpdate(skillTimeUpdateRequest);
	        
	        return "redirect:/skilledit";
	    }

	 


}