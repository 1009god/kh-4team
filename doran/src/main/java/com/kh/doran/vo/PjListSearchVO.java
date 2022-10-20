package com.kh.doran.vo;

import lombok.Data;
import lombok.ToString;

@Data
public class PjListSearchVO {
	//검색 분류와 검색어
	private String type, keyword;
	
	@ToString.Include
	public boolean isSearch() {
		return type!=null && keyword!=null;
	}

}