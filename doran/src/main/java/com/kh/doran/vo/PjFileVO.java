package com.kh.doran.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PjFileVO {
	
	private int pjNo;
	private int pjSellerMemNo;
	private String pjCategory;
	private String pjName;
	private String pjSummary;
	private int pjTargetMoney;
	private Date pjFundingStartDate;
	private Date pjFundingEndDate;
	private Date pjEndDate;
	private int pjLikesNumber;
	private int pjFilePjNo;
	private int PjFileNo;
	private String PjFileClassify;
	private MultipartFile files1;
	private MultipartFile files2;
	private MultipartFile files3;
}
