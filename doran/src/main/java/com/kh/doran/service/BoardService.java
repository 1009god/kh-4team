package com.kh.doran.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.kh.doran.entity.BoardDto;


public interface BoardService {
	int write(BoardDto boardDto, List<MultipartFile> files) 
			throws IllegalStateException, IOException;
	
	boolean remove(int boardPostNo);
}
