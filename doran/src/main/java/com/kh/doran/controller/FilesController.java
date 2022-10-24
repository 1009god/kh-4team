package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.doran.repository.FilesDao;

@RestController //컨트롤러+리스폰스바디
@RequestMapping("/files")
public class FilesController {

	@Autowired
	private FilesDao filesDao;
	
	//private final File dir = new File("D:/doranupload");
}
