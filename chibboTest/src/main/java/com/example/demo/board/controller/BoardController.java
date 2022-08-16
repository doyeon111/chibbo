package com.example.demo.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.board.service.BoardService;
import com.example.demo.board.vo.Board;
import com.example.demo.userinfo.service.UserInfoService;
import com.example.demo.userinfo.vo.UserInfo;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;


@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired 
	private UserInfoService userinfoService;
	
	
	//페이징 처리와 관련된 변수
	/*
	 * public int pageSIZE = 10; //한 화면에 보여줄 레코드의 수 public int totalRecord = 0; //
	 * 전체 레코드의 수 public int totalPage = 1; // 전체 페이지의 수
	 */	
	
	
	//@ModelAttribute는 @RequestMapping이 실행되기 전 Model의 "userinfo" 키 값을 초기화해주는 역할을 합니다.
	@ModelAttribute("userinfo")
	public UserInfo setUserInfo() {
		//System.out.println("****setUserInfo()****");
		return new UserInfo();
	}

	
	
	@RequestMapping("/listBoard")
	public void listBoard(Model model, @RequestParam(value="pageNUM", defaultValue = "1") int pageNUM) { // pageNUM의 기본페이지는 1페이지로 설정
//		System.out.println("pageNUM: " + pageNUM);
//		totalRecord = boardService.totalRecord();
//		
//		//레코드 나누기 사이즈를 하였을 때 나머지 수가 있으면 반올림
//		totalPage = (int)Math.ceil(totalRecord / (double) pageSIZE); 
//		
//		int start = (pageNUM - 1) * pageSIZE + 1;
//		int end = start + pageSIZE - 1;
//		
//		if(end > totalRecord) {
//			end = totalRecord;
//		}
		
//		HashMap map = new HashMap();
//		map.put("start", start);
//		map.put("end", end);
//		
//		model.addAttribute("totalPage", totalPage);
		model.addAttribute("list", boardService.listBoard());
	} 
	
	
	@GetMapping("/categorylistBoard/{boardNo}") //boardNo가 아닌 명으로 불러오기
	public ModelAndView goCategory(@PathVariable int boardNo, Model model, Board b) {
		ModelAndView mav = new ModelAndView("categorylistBoard");
		
		List<Board> boardCategory = boardService.goCategory(boardNo);
		model.addAttribute("boardCategory", boardCategory);
		model.addAttribute("listLength", boardCategory.size()); //totalsize 
		mav.addObject("u", userinfoService.findByUserNo((Integer)b.getUserNo()));
		model.addAttribute("boardNo", (Integer)boardNo);
		//System.out.println(boardCategory);
		
		return mav;
	}
	
	
	@RequestMapping("/detailBoard/{boardNo}/{boardPostNo}")
	public ModelAndView detailBoard(Model model, @PathVariable int boardPostNo, @PathVariable int boardNo) {
		ModelAndView mav = new ModelAndView("detailBoard");
		model.addAttribute("boardNo", (Integer)boardNo);
		Board b = boardService.findByNo(boardPostNo);
		model.addAttribute("b", b);
		//System.out.println(b);
		model.addAttribute(boardService.updateHit(boardPostNo)); // 조회수를 증가할 시 중복방지를 설정해야함!!!!!!(쿠키로 설정해야함)
		mav.addObject("u", userinfoService.findByUserNo((Integer)b.getUserNo()));
		
		
		//이미지 파일인지 아닌지 상태유지
		String fname = b.getBoardFname();
				
		//첨부파일이 있었다면
		if(fname != null && !fname.equals("")) {
			//파일 이름의 끝자리가 png, jpg, gif로 끝나면 img로 상태유지
			if(fname.endsWith(".png") || fname.endsWith(".jpg") || fname.endsWith(".gif")) { 
				model.addAttribute("img", "yes");
			}
		}
		return mav;
	}
	
	
	@GetMapping("/insertBoard/{boardNo}") //insertsubmit이 아닌 그냥 폼만 불러올땐 boardNo를 따로 처리하지 않아도 된다.
	public String insertBoard(Model model, @RequestParam(value= "boardPostNo", defaultValue = "0") int boardPostNo, @PathVariable int boardNo,  HttpSession session) {
		//ModelAndView mav = new ModelAndView("insertBoard");
		//model.addAttribute("u", userinfoService.findByUserNo(userNo));
		model.addAttribute("boardNo", (Integer)boardNo);
		boardPostNo = boardService.getNextNo();
		//System.out.println(boardPostNo);
		model.addAttribute("boardPostNo", boardService.getNextNo());

		return "insertBoard";
	}
	
	@PostMapping("/insertBoard/{boardNo}")
	public ModelAndView insertSubmit(Board b, HttpServletRequest request, HttpSession session, Model model, @PathVariable int boardNo) {
		//model.addAttribute("boardNo", boardService.goCategory(boardNo));
		b.setBoardNo(boardNo);
		b.setUserNo((Integer)(session.getAttribute("userNo")));
		model.addAttribute("boardNo", (Integer)boardNo);
		//업로드한 실경로 알아오기
		String path = request.getRealPath("upload");
		System.out.println(path); 
				
		MultipartFile uploadFile = b.getBoardUploadFile();
		String fname = null;
		fname = uploadFile.getOriginalFilename(); //파일이름을 읽어온다.";
		System.out.println(fname);
				
		if(fname != null && !fname.equals("")) { //업로드한 파일이 있다면
			//업로드한 파일이 있어야지만 여기로 오게끔
			System.out.println("업로드한 파일이 있어요!");
			b.setBoardFname(fname); //업로드한 파일이 있을경우 그 파일 이름을 실어준다.
					
					
			try {
				byte []data = uploadFile.getBytes(); // 업로드한 파일의 내용을 가지고온다.
				FileOutputStream fos = new FileOutputStream(path + "/" + fname); //출력하기 위한 용도
				fos.write(data); //데이터에 있는 내용을 출력
				fos.close();
						
					
			} catch(Exception e) {
				System.out.println("예외발생: " + e.getMessage());
			}
		} else {
			System.out.println("업로드한 파일이 없어요!");
			b.setBoardFname(""); //파일 이름이 없을 경우 ""
		}
		
		
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		
		
		int boardPostNo = boardService.getNextNo();
		System.out.println(boardPostNo);
		b.setBoardPostNo(boardPostNo);
		
		int re = boardService.insertBoard(b);
		if(re != 1) {
			mav.addObject("msg", "게시물 등록에 실패하였습니다.");
		}
		return mav;
	}
	
	
	@GetMapping("/updateBoard/{boardPostNo}")
	public String updateBoard(Model model, @PathVariable int boardPostNo) {
		model.addAttribute("b", boardService.findByNo(boardPostNo)); 
//		model.addAttribute("boardNo", boardService.goCategory(boardNo));
		return "/updateBoard";
		
	}
	
	
	@PostMapping("/updateBoard/{boardPostNo}")
	public ModelAndView updateSubmit(Board b, HttpServletRequest request, HttpSession session, Model model, @PathVariable int boardPostNo) {
		
		boardService.findByNo(boardPostNo);
		//업로드한 실경로 알아오기
		String path = request.getRealPath("upload");
		System.out.println(path);
						
		String oldFname = b.getBoardFname(); //원래의 파일이름
		System.out.println(oldFname);
		String fname = null; //업로드할 파일을 받아오는 변수
		MultipartFile uploadFile = b.getBoardUploadFile();
		fname = uploadFile.getOriginalFilename(); //파일이름을 읽어온다.
		System.out.println(fname);
				
		if(fname != null && !fname.equals("")) {
			b.setBoardFname(fname); //업로드한 파일이름으로 변경
					
			try {
				byte []data = uploadFile.getBytes(); // 업로드한 파일의 내용을 가지고온다.
				FileOutputStream fos = new FileOutputStream(path + "/" + fname); //파일의 경로를 찾아 출력하기 위한 용도
				fos.write(data); //파일에 있는 내용을 출력
				fos.close();
			
							
			} catch(Exception e) {
				System.out.println("예외발생: " + e.getMessage());
			}
		}
				
		
		
		
		
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		
		int userNo = (int)session.getAttribute("userNo");
		session.getAttribute("userNo");
		System.out.println(userNo);
		b.setUserNo(userNo);
		
		
		int re = boardService.updateBoard(b);
		
		if(re != 1) {
			mav.addObject("msg", "게시물 수정에 실패하였습니다.");
			
			if(fname != null && !fname.equals("")) { //업로드한 파일을 삭제
				File file = new File(path + "/" + oldFname); //파일의 경로를 찾은 후
				System.out.println(file);
				file.delete(); //원래 파일을 삭제
			}
		} else {
			//파일을 수정하고 원래 게시물에 파일이 있었다면
			if(fname != null && !fname.equals("") && oldFname != null && !oldFname.equals("")) { 
				File file = new File(path + "/" + oldFname); //파일의 경로를 찾은 후
				file.delete(); //원래 파일을 삭제
			}
		}
		
		return mav;

	}
	
	
	@RequestMapping("/deleteBoard/{boardPostNo}") 
	public ModelAndView deleteBoard(@PathVariable("boardPostNo") int boardPostNo, Model model, HttpSession session, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		
		
		//model.addAttribute("b", boardService.deleteBoard(boardPostNo));
		
		UserInfo user = (UserInfo)session.getAttribute("userInfo");
		
//		String userid = user.getUserId();
//		System.out.println("userid:" + userid);
		
		//실제경로를 알아오기
		String path = request.getRealPath("upload");
						
		//지울 파일이름을 가져오기
		String oldFname = boardService.findByNo(boardPostNo).getBoardFname();
		System.out.println(oldFname);
		
		int re = boardService.deleteBoard(boardPostNo);
		
		if(re != 1) {
			mav.addObject("msg", "게시물 삭제에 실패하였습니다.");
		} else {
			//만약 게시물 삭제에 성공했다면 해당 파일을 삭제
			if(oldFname != null && !oldFname.equals("")) {
				File file = new File(path + "/" + oldFname);
				file.delete();
			}
			
		}
		
		return mav;
	}
	
	
}
