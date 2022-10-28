package com.kh.doran.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class NoticeDto {
	private int noticeNo, noticeAdminNo;
	private String noticeTitle, noticeContent;
	private Date noticeWriteTime;

}
