package com.kh.doran.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemListSearchVO {

	private int MemNo;
	private String memEmail,memNick,sellerMemNo;
}
