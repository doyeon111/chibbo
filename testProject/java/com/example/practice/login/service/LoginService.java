package com.example.practice.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.practice.login.domain.Login;
import com.example.practice.login.mapper.LoginMapper;

@Service
//@Transactional
public class LoginService {
	@Autowired
	private LoginMapper loginMapper;
	
	public Login loginCheck(Login login) {
		return loginMapper.loginCheck(login);
	}

}
