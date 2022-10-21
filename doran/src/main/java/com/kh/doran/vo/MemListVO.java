package com.kh.doran.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MemListVO {

	private int memNo;
	private String memEmail,memNick,sellerCheck;
}
