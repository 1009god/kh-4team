package com.kh.doran.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class likeVO {
	
	private int pjNo, sellerMemNo;
	private String pjName, pjCategory;
	
	
}
