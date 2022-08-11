package com.example.demo.userinfo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exception.lwException;
import com.example.demo.userinfo.service.UserInfoService;
import com.example.demo.userinfo.vo.UserInfo;


@Controller
public class UserInfoController {
	@Autowired
	private UserInfoService userinfoService;
	
	//로그인 화면 반환
	@RequestMapping("/login")
	public String login() throws Exception { 
		return "/login";
	}
	
	// 로그인
    @RequestMapping("/loginCheck")
    public String loginCheck(UserInfo userinfo, Model model, HttpSession session) {
    	
    	UserInfo user = userinfoService.loginCheck(userinfo);
    	System.out.println(user);
    	
    	if(user != null) {
    		//입력값과 db값 비교
    		if(userinfo.getUserPassword() == user.getUserPassword() || userinfo.getUserPassword().equals(user.getUserPassword())) {
    			model.addAttribute("userInfo", user);
    			session.setAttribute("userInfo", user);
    			session.setAttribute("userNo", user.getUserNo());
    		}else {
    		throw new lwException("로그인 실패!");
    		}
    	}else {
    		throw new lwException("로그인 실패!");
    	}
    	
    	
    	return "redirect:/home";
    }
    
    // 로그아웃
    @RequestMapping("/logout")
    public String logOut(UserInfo userinfo, Model model, HttpSession session) {
    	UserInfo logout = (UserInfo)session.getAttribute("userInfo");
    	
    	if(logout != null) {
    		session.invalidate(); 
    		System.out.println("userInfo" + logout);
    	}
    	
    	return "redirect:/login";
    }

    //회원가입 폼
    @RequestMapping(value="/signUp", method=RequestMethod.GET)
    public void signUpForm(Model model, UserInfo userinfo) {
    	
    }
    
    //회원가입 insert될 때
    @RequestMapping(value="/signUp", method=RequestMethod.POST)
    public ModelAndView signUpSubmit(UserInfo userinfo, HttpServletRequest request) {
    	//회원가입 시 자동으로 회원번호 생성
    	int UserNo = userinfoService.getNextNo();
    	userinfo.setUserNo(UserNo);
    	
    	userinfo.setUserRole("user"); //회원가입할 때 사용자들은 user라는 권한을 갖도록 자동으로 role을 설정해줌 ** 관리자 설정
    	
    	ModelAndView mav = new ModelAndView("redirect:/login");
    	int re = userinfoService.signUp(userinfo);
    	
    	
    	
    	if(re != 1) {
			mav.addObject("msg", "회원가입을 실패하였습니다.");
			//mav.setViewName("error");
		} 
    	
    	return mav;
    }
    
    //회원의 id를 찾기
    @ResponseBody
    @GetMapping("/findByUserId/{userId}")
    public UserInfo findByUserId(@PathVariable String userId) {
    	UserInfo userinfo = userinfoService.findByUserId(userId);
    	//session.setAttribute("userNo", userinfo.getUserNo());
    	return userinfo;
    	

    }
    
    //회원번호 찾기
    @ResponseBody
    @GetMapping("/findByUserNo/{userNo}")
    public UserInfo findByUserNo(@PathVariable int userNo) {
    	UserInfo userinfo = userinfoService.findByUserNo(userNo);
    	return userinfo;
    }
    
    //모든 회원목록 출력
    @ResponseBody
    @GetMapping("/findAll")
	public List<UserInfo> findAll() {
    	return userinfoService.findAll();
	}
    
    
}
