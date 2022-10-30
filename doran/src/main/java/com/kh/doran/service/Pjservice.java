package com.kh.doran.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.OptionsDto;
import com.kh.doran.entity.PjDto;

public interface Pjservice {
	int insert(PjDto pjDto, List<MultipartFile> files, OptionsDto optionsDto );
}
