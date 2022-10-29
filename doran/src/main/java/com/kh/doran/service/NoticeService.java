package com.kh.doran.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.NoticeDto;

public interface NoticeService {
	int write(NoticeDto noticeDto, List<MultipartFile> files) throws IllegalStateException, IOException;

	boolean remove(int noticeNo);
}
