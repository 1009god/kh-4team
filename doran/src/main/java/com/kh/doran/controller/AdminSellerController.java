package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.entity.SellerDto;
import com.kh.doran.repository.AdminSellerDao;

@Controller
@RequestMapping("/admin")
public class AdminSellerController {

	@Autowired
	private AdminSellerDao adminSellerDao;

	@RequestMapping("/sellerlist")
	public String list(Model model, @RequestParam(required = false) String type,
			@RequestParam(required = false) String keyword) {
		boolean isSearch = type != null && keyword != null;
		if (isSearch) {
			model.addAttribute("list", adminSellerDao.selectList(type, keyword));
		} else {
			model.addAttribute("list", adminSellerDao.selectList());
		}
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
