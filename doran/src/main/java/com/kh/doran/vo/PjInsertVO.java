package com.kh.doran.vo;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.OptionsDto;

import lombok.Data;

//Project 등록용 VO
@Data
public class PjInsertVO {
	private String pjCategory;
	private String pjName;
	private String pjSummary;
	private int pjTargetMoney;
	private Date pjFundingStartDate;
	private Date pjFundingEndDate;
	private Date pjEndDate;
	
	private int sellerMemNo;
	
	private List<OptionsDto> options;
	
	private MultipartFile pjFileBanner;
	private MultipartFile pjFileIntro;
	private List<MultipartFile> pjFileMains;
}

