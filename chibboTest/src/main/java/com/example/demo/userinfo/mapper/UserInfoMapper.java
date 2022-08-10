package com.example.demo.userinfo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.userinfo.vo.UserInfo;

@Repository
public interface UserInfoMapper {
	
	//로그인
	public UserInfo loginCheck(UserInfo userinfo);
	
	//회원가입
	public int signUp(UserInfo userinfo);
	
	//사용자번호 자동생성
	public int getNextNo();
	 
	//회원 아이디 찾기
	public UserInfo findByUserId(String userId);
	
	
	//회원 번호 찾기
	public UserInfo findByUserNo(int userNo);
	
	
	//모든 회원의 목록을 출력
	public List<UserInfo> findAll();
}
