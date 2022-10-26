package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminMemListVO {

	private int memNo;
	private String memEmail, memPw, memNick, memTel, memRoute;
	private Date memJoinDate;
	private String sellerCheck;
}
