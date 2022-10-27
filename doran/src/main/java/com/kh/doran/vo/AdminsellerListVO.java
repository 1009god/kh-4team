package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminsellerListVO {

	
	private String memNick;
	private int sellerMemNo; //멤버번호가 외래키
	private Date sellerRegistryDate; //디폴트 sysdate
	private String sellerBank; //계좌
	private String sellerAccount; //계좌
	private String sellerCheck; //디폴트 대기 체크값으로 대기, 승인, 거절
	
	
}
