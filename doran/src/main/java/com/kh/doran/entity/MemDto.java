package com.kh.doran.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemDto {
	
	private int memNo;
	private String memEmail, memPw, memNick, memTel, memRoute;
	private Date memJoinDate;
}
