package com.example.practice.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.example.practice.exception.lwException;
import com.example.practice.login.domain.Login;
import com.example.practice.login.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	// 로그인 화면
    @RequestMapping("/login")
	public String Login() throws Exception {
		return "login";
	}
    
    // 로그인
    @RequestMapping("/loginCheck")
    public String loginCheck(Login login, Model model, HttpSession session) {
    	
    	Login user = loginService.loginCheck(login);
    	
    	if(user != null) {
    		if(login.getUser_pwd() == user.getUser_pwd() || login.getUser_pwd().equals(user.getUser_pwd())) {
    			model.addAttribute("userInfo", user);
    			session.setAttribute("userInfo", user);
    		}else {
    			throw new lwException("로그인 실패!");
    		}
    	}else {
    		throw new lwException("로그인 실패!");
    	}
    	
    	
    	return "redirect:/home";
    }
    
    // 로그아웃
    @RequestMapping("/logOut")
    public String logOut(Login login, Model model, HttpSession session) {
    	Login lg = (Login)session.getAttribute("userInfo");
    	
    	if(lg != null) {
    		session.invalidate();
    		System.out.println("userInfo" + lg);
    	}
    	
    	return "redirect:/login";
    }
}
