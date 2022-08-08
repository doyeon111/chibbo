package com.example.practice.login.mapper;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.example.practice.login.domain.Login;

@Repository
public interface LoginMapper {

	public Login loginCheck(Login login);

}

