package com.example.demo.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.board.mapper.BoardMapper;
import com.example.demo.board.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	//게시물 목록
	public List<Board> listBoard(HashMap map) {
		List<Board> list = null;
		return boardMapper.listBoard(map);
	}
	 
	//게시물 등록
	public int insertBoard(Board b) {
		return boardMapper.insertBoard(b);
	}
	
	
	//게시물 번호 자동생성
	public int getNextNo() {
		int boardPostNo = 0;
		return boardMapper.getNextNo();
	}
	
	//게시물 번호 찾기
	public Board findByNo(int boardPostNo) {
		Board b = null;
		return boardMapper.findByNo(boardPostNo);
	}
	
	//게시물 수정
	public int updateBoard(Board b) {
		return boardMapper.updateBoard(b);
	}
	
	//게시물 삭제
	public int deleteBoard(int boardPostNo) {
		return boardMapper.deleteBoard(boardPostNo);
		
	}
	
	//게시물 조회수 증가
	public int updateHit(int boardPostNo) {
		return boardMapper.updateHit(boardPostNo);
	}
	
	
	//게시판 카테고리 누를 시 이동
	public List<Board> goCategory(int boardNo) {
		return boardMapper.goCategory(boardNo);
	}
	
	//페이징 처리를 위한 전체 레코드 수 
	public int totalRecord() {
		return boardMapper.totalRecord();
	}
	
}
