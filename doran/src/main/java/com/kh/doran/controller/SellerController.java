package com.kh.doran.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.FilesDto;
import com.kh.doran.entity.PjDto;
import com.kh.doran.entity.SellerDto;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.repository.SellerDao;
import com.kh.doran.service.SellerService;



@Controller
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private FilesDao filesDao;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private PjDao pjDao;
	
	private final File directory =new File("D:/doranupload");
	@PostConstruct
	public void prepare() {
		directory.mkdirs();
	}
	
	
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
			) throws IllegalStateException, IOException {
		//SellerDto findDto = sellerDao.
		sellerDao.insert(sellerDto);
		for(MultipartFile file : files) {
			if(!file.isEmpty()) {
				System.out.println("첨부파일 발견");
				
			//db등록
			int filesNo = filesDao.sequence();
			filesDao.insert(FilesDto.builder()
					.filesNo(filesNo)
					.filesUploadname(file.getOriginalFilename())
					.filesType(file.getContentType())
					.filesSize(file.getSize())
					.build());
			//파일저장
			File dir = new File("D:/doranuplaod/sellerfiles");
			dir.mkdirs();
			File target = new File(dir,String.valueOf(filesNo));
			file.transferTo(target);
			}
		}
		
		
		return "redircet:/";
	}
	
	
	
}
