package com.kh.doran.controller;

import java.util.HashSet;
import java.util.Set;

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

import com.kh.doran.entity.DoranQDto;
import com.kh.doran.error.TargetNotFoundException;
import com.kh.doran.repository.DoranQDao;
import com.kh.doran.vo.DoranQListSearchVO;

@Controller
@RequestMapping("/doranq")
public class DoranQController {

	@Autowired
	private DoranQDao doranQDao;
	
	@GetMapping("/write")
	public String write() {
		return "doranq/write";
	}
	
	@PostMapping("/write") 
	public String write(
			@ModelAttribute DoranQDto doranQDto,
			HttpSession session, RedirectAttributes attr) {
		//session 에 있는 회원 번호를 작성자로 추가한 뒤 등록해야 함
		int memNo = (int)session.getAttribute("loginNo");
		doranQDto.setDoranQmemNo(memNo);
		
		doranQDao.insert(doranQDto);
		return "redirect:detail";
		
		//문제점 : 등록은 되는데 몇 번인지 알 수 없다
		//해결책 : 번호를 미리 생성하고 등록하도록 메소드 변경
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam int doranQNo) {
		boolean result = doranQDao.delete(doranQNo);
	      if(result) {//삭제 성공
	         return "";
	      }
	      else {//구문은 실행되었지만 바뀐 게 없는 경우 (강제 예외 처리)
	         throw new TargetNotFoundException();
	      }
		
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int doranQNo, Model model) {
		DoranQDto doranQDto = doranQDao.selectOne(doranQNo);
		if(doranQDto == null) { //없는 경우 내가 만든 예외 발생
			throw new TargetNotFoundException();
		}
		model.addAttribute("DoranQDto", doranQDto);
		return "doranq/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute DoranQDto doranQDto,
			RedirectAttributes attr) {
		boolean result = doranQDao.update(doranQDto);
		if(result) {//성공했으면 상세페이지
			attr.addAttribute("doranQNo", doranQDto.getDoranQNo());
			return "redirect:detail";
		}
		
		else { //실패했으면 오류 발생
			throw new TargetNotFoundException();
		}
	}
	
}
