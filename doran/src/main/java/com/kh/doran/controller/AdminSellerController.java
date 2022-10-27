package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.entity.MemDto;
import com.kh.doran.entity.SellerDto;
import com.kh.doran.repository.AdminSellerDao;
import com.kh.doran.vo.MemListSearchVO;
import com.kh.doran.vo.SellerListSearchVO;

@Controller
@RequestMapping("/admin")
public class AdminSellerController {

	@Autowired
	private AdminSellerDao adminSellerDao;
	
	

	@RequestMapping("/sellerlist")
	public String list(Model model, 
			@ModelAttribute(name="vo") SellerListSearchVO vo) {
		
		//페이지 네비게이터를 위한 게시글 수를 구한 것
		int count = adminSellerDao.count(vo);
		vo.setCount(count);
	
		model.addAttribute("list",adminSellerDao.selectList(vo));
		return "admin/sellerlist";
	}
	
	@GetMapping("/sellerdetail")
	public String detail(Model model, @RequestParam int sellerNo) {
		SellerDto sellerDto = adminSellerDao.selectOne(sellerNo);
		model.addAttribute("sellerDto",sellerDto);
		
		return "admin/sellerdetail";
	}
	
	@GetMapping("/sellerdelete")
	public String delete(@RequestParam int sellerNo) {
		boolean result = adminSellerDao.delete(sellerNo);
		if(result) {
			return "redirect:sellerlist";
		}
		else {
			return "admin/editSellerFail";
		}
	}
	
}
