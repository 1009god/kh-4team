package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SupportListVO {

	private int ordersNo, ordersMemNo, pjNo, pjSellerMemNo;
	private String pjCategory, pjName, memNick;
	private Date ordersCancelDate;
	
	
	//supportPJVo 아무도 안쓰면 삭제 해도 되나?
}
