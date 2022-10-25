package com.kh.doran.vo;

import lombok.Data;

@Data 
public class BoardListSearchVO {
	private String type, keyword;
	
	public boolean isSearch() {
		return type != null && keyword != null;
	}

}
