package com.example.practice;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.practice.login.domain.Login;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String index(Model model, HttpSession session) {
		Login login = (Login)session.getAttribute("userInfo");
//		Login login = (Login)session.getAttribute("userInfo");
//		
		if(login != null) {
			model.addAttribute("userInfo", login);
			System.out.println("인덱스 가즈아" + login);
		}
		
		return "index";
	}
}
