package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AdminsellerDetailVO {

	private int sellerMemNo;
	private String memEmail, memPw, memNick, memTel, memRoute;
	private Date memJoinDate;
	private Date sellerRegistryDate; //디폴트 sysdate
	private String sellerBank; //계좌
	private String sellerAccount; //계좌
	private String sellerCheck; //디폴트 대기 체크값으로 대기, 승인, 거절
}
