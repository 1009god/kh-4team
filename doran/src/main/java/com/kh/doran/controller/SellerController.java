package com.kh.doran.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.SellerDto;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.SellerDao;



@Controller
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private FilesDao filesDao;
	
	
	
	@GetMapping("/sellerjoin")
	public String sellerjoin(){
		return "seller/sellerjoin";
	}
	
	@PostMapping("/sellerjoin")
	public String sellerjoin(
			@ModelAttribute SellerDto sellerDto,
			HttpSession session,
			@RequestParam List<MultipartFile> files
			//리퀘스트파람 멀티파트파일 멤버프로필
			){
		sellerDao.insert(sellerDto);
		//판매자 첨부파일 저장
		//첨부파일이 없어도 리스트에는 1개의 객체가 들어있다
		System.out.println();
		
		return "seller/sellerfinish";
	}
}
