package com.example.demo.board.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Board {
	private int boardPostNo;
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardHit;
	private Date boardCreateDate;
	private Date boardupdateDate;
	private String boardFname;
	private MultipartFile boardUploadFile;
	private int userNo;
}
 