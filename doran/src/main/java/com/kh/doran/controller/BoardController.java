package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.entity.MemDto;
import com.kh.doran.repository.BoardDao;
import com.kh.doran.vo.BoardListSearchVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	
	//modelAttribute 로 수신한 데이터는 자동으로 model 에 첨부됨
	//- 옵션에 name 을 작성하면 해당하는 이름으로 model 에 첨부
	@RequestMapping("/list")
	public String list(Model model,
			@ModelAttribute(name="vo") BoardListSearchVO vo) {
		if(vo.isSearch()) {
			model.addAttribute("list", boardDao.selectList(vo));
		}
		else {
			model.addAttribute("list", boardDao.selectList());
		}
		return "board/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam int boardPostNo, Model model) {
//		1. 조회수를 증가시켜서 데이터를 불러온다
//		boardDao.updateReadcount(boardPostNo); //조회수 증가
//		model.addAttribute("boardDto", boardDao.selectOne(boardPostNo)); //불러와
		
		//2. 데이터를 읽도록 처리한다
		model.addAttribute("boardDto", boardDao.read(boardPostNo)); 
		return "board/detail";
	}
	
	@GetMapping("/write")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/write") 
	public String write(
			@ModelAttribute BoardDto boardDto,
			HttpSession session) {
		//session 에 있는 회원 번호를 작성자로 추가한 뒤 등록해야 함
		int memNo = (int)session.getAttribute("loginNo");
		boardDto.setBoardMemNo(memNo);
		
		boardDao.insert(boardDto);
		return "redirect:list";
	}
}
		

