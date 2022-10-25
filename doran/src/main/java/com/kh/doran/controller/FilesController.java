package com.kh.doran.controller;



import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.FilesDto;
import com.kh.doran.repository.FilesDao;




@Controller
@RequestMapping("/files")
//파일즈라는 주소가 앞에 붙는다
public class FilesController {
	
	@Autowired
	private FilesDao filesDao;

	
	
	//컨트롤러에서의 파일 수신 처리
		// -> Multipart Request를 처리한다고 부름
		// -> Spring boot 에서 내부적으로 multipartResolver를 등록
		// -> Spring Controller에서는 MultipartFile 형태로 파일을 수신
		// -> 기존에 사용하던 annotation 전부 지원
	@GetMapping("/root")
	public String root() {
		return "files/root";
	}
	
//	@PostMapping("/upload")
//	public String uplaod(@RequestParam MultipartFile files) throws IllegalStateException, IOException {
//		//db저장
//		int filesNo = filesDao.sequence();
//		filesDao.insert(FilesDto.builder()
//				.filesNo(filesNo)
//				.filesUploadname(files.getOriginalFilename())
//				.filesType(files.getContentType())
//				.filesSize(files.getSize())
//				.build());
//		
//		//파일저장
//		File dir = new File("D:/doranupload");
//		dir.mkdir();
//		File target = new File(dir,String.valueOf(filesNo));
//		files.transferTo(target);
		//이러면 
		//이름이 문제인데 이름을 시퀀스로 왜 하냐 사용자한테 보내줄 이름은 db에 넣었기에 이제부터 파일 이름을 시퀀스로 저장해도 좋다
				
//		return "redirect:/";
//	}

}
