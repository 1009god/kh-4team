package com.kh.doran.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import com.kh.doran.entity.SellerDto;
import com.kh.doran.repository.SellerDao;



@Controller
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private SellerDao sellerDao;
	
	@GetMapping("/sellerjoin")
	public String sellerjoin(){
		return "seller/sellerjoin";
	}
	
	@PostMapping("/sellerjoin")
	public String sellerjoin(
			@ModelAttribute SellerDto sellerDto,
			HttpSession session){
		sellerDao.insert(sellerDto);
		
		return "seller/sellerfinish";
	}
}
