package com.kh.doran.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SupportListVO {

	private int ordersNo, ordersMemNo, pjNo, pjSellerMemNo;
	private String pjCategory, pjName, memNick;
	
	
	//supportPJVo 아무도 안쓰면 삭제 해도 되나?
}
