package com.example.demo.userinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.userinfo.mapper.UserInfoMapper;
import com.example.demo.userinfo.vo.UserInfo;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoMapper userinfoMapper;
	
	public UserInfo loginCheck(UserInfo userinfo) {
		return userinfoMapper.loginCheck(userinfo);
	}
	
	public int signUp(UserInfo userinfo) {
		return userinfoMapper.signUp(userinfo);
	}
	
	public int getNextNo() {
		return userinfoMapper.getNextNo();
	}

	public UserInfo findByUserId(String userId) {
		
		return userinfoMapper.findByUserId(userId);
	}
	
	public UserInfo findByUserNo(int userNo) {
		
		return userinfoMapper.findByUserNo(userNo);
	}
	
	public List<UserInfo> findAll() {
		return userinfoMapper.findAll();
	}
}
