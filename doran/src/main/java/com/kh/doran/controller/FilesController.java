package com.kh.doran.controller;




import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kh.doran.service.FilesService;




@RestController //컨트롤러+리스폰스바디
@RequestMapping("/files")
public class FilesController {
	
	
	@Autowired
	private FilesService filesService;
	
	@GetMapping("/download/{filesNo}")
	public ResponseEntity<ByteArrayResource> download(
			@PathVariable int filesNo) throws IOException{
		return filesService.load(filesNo);
	}
	
	
	

}
