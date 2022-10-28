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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.doran.entity.NoticeDto;
import com.kh.doran.error.TargetNotFoundException;
import com.kh.doran.repository.NoticeDao;
import com.kh.doran.vo.NoticeListSearchVO;


@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/list")
	public String list(Model model,
			@ModelAttribute(name="vo") NoticeListSearchVO vo) {
		//페이지 네비게이터를 위한 게시글 수를 구한다
		int count = noticeDao.count(vo);
		vo.setCount(count);
		
		model.addAttribute("list", noticeDao.selectList(vo));
		return "notice/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam int noticeNo, Model model) {
		model.addAttribute("noticeDto", noticeDao.selectOne(noticeNo)); 
		return "notice/detail";
	}
	
	@GetMapping("/write")
	public String write() {
		return "notice/write";
	}
	
	@PostMapping("/write") 
	public String write(
			@ModelAttribute NoticeDto noticeDto,
			HttpSession session, RedirectAttributes attr) {
		//session 에 있는 회원 번호를 작성자로 추가한 뒤 등록해야 함
		int adminNo = (int)session.getAttribute("loginNo");
		noticeDto.setNoticeAdminNo(adminNo);
		
//		noticeDao.insert(noticeDto);
		//return "redirect:list";
		
		//문제점 : 등록은 되는데 몇 번인지 알 수 없다
		//해결책 : 번호를 미리 생성하고 등록하도록 메소드 변경
		int noticeNo = noticeDao.insert2(noticeDto);
		attr.addAttribute("noticeNo", noticeNo);
		return "redirect:detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int noticeNo) {
		boolean result = noticeDao.delete(noticeNo);
	      if(result) {//삭제 성공
	         return "redirect:list";
	      }
	      else {//구문은 실행되었지만 바뀐 게 없는 경우 (강제 예외 처리)
	         throw new TargetNotFoundException();
	      }
		
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int noticeNo, Model model) {
		NoticeDto noticeDto = noticeDao.selectOne(noticeNo);
		if(noticeDto == null) { //없는 경우 내가 만든 예외 발생
			throw new TargetNotFoundException();
		}
		model.addAttribute("noticeDto", noticeDto);
		return "board/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute NoticeDto noticeDto,
			RedirectAttributes attr) {
		boolean result = noticeDao.update(noticeDto);
		if(result) {//성공했으면 상세페이지
			attr.addAttribute("noticeNo", noticeDto.getNoticeNo());
			return "redirect:detail";
		}
		
		else { //실패했으면 오류 발생
			throw new TargetNotFoundException();
		}
	}
	
}
