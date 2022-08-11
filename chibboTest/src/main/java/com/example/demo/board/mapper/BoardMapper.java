package com.example.demo.board.mapper;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.board.vo.Board;

@Repository
public interface BoardMapper {
	
	//게시물 전체 조회
	public List<Board> listBoard();
	
	//게시물 작성
	public int insertBoard(Board b); 
	
	//게시물 하나의 번호 가져오기
	public Board findByNo(int boardPostNo);
	
	//게시물 번호 자동생성
	public int getNextNo();
	
	//게시물 수정
	public int updateBoard(Board b);
	
	//게시물 삭제
	public int deleteBoard(int boardPostNo);
	
	//게시물 조회수 증가
	public int updateHit(int boardPostNo);
	
	//게시판 카테고리 눌렀을 시 이동
	public List<Board> goCategory(int boardNo);
	
	//전체 레코드 수를 위한 메소드(페이징 처리)
	public int totalRecord();
	
	@Transactional
	public Page<Board> getBoardList(Pageable pageable);
}
