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

import com.kh.doran.entity.SellerQDto;
import com.kh.doran.repository.PjDao;
import com.kh.doran.repository.SellerQDao;

@Controller
@RequestMapping("/sellerq")
public class SellerQController {
	
	@Autowired
	private SellerQDao sellerQDao;
	
	@Autowired
	private PjDao pjDao;
	
	//판매자에게 문의 작성 페이지
	@GetMapping("/write")
	public String write(@RequestParam int pjNo,
										HttpSession session,
										Model model) {
		//pjno로 셀렉트원 해서 
		
		model.addAttribute("PjDto", pjDao.selectOne(pjNo));
		
		return "sellerq/write";
	}	
	
	@PostMapping("/write")
	public String write(HttpSession session, 
									//@RequestParam int pjNo,				
									@ModelAttribute SellerQDto sellerQDto, 
									Model model,
									RedirectAttributes attr) {
		//session에서 글을 작성할 회원 번호를 넘겨준다
		
		int memNo = (int)session.getAttribute("loginNo");
		sellerQDto.setSellerQMemNo(memNo); //작성하는 회원의 번호
		
		//model.addAttribute("PjDto", pjDao.selectOne(pjNo));
		
		sellerQDao.insert(sellerQDto);  
		return "redirect:write_finish";
	}
			
	
		@GetMapping("/write_finish")
		public String writeFinish() {
			return "/";// 이 주소 수정해야함
		}
		
		
		@GetMapping("/list")
		public String list(@RequestParam int pjNo ) {	
			// pjNo으로 vo에 담아오거나 Dto에 담아오는 거 
			
			
			
			return "sellerq/list";	
		}
		
		
		
		
		
		
	}
	


