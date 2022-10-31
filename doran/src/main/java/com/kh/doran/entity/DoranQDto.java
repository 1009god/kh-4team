package com.kh.doran.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DoranQDto {

	private int doranQNo, doranQMemNo, doranQAdminNo;
	private String doranQTitle, doranQContent;
	private Date doranQWritetime;
	private String doranQType,doranQProcessing;

	//계층형 게시판 데이터
	private int doranQGroup, doranQParent, doranQDepth;
}
