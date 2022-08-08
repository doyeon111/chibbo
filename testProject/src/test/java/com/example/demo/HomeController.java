package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.userinfo.vo.UserInfo;

@Controller
public class HomeController {
	@RequestMapping("/home")
	public String home(Model model, HttpSession session) {
		//로그인 세션유지
		UserInfo login = (UserInfo)session.getAttribute("userInfo");
		if(login != null) {
			model.addAttribute("userInfo", login);
		}
		
		return "index";
	}
}
