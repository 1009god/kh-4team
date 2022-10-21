package com.kh.doran.vo;

public class PjSortVO {

	//정렬 분류
	private String sort;
	
	public boolean isPopular() {
		return sort!=null;
	}
	
	//정렬 기준
	public String sortBy() {
		if(isPopular()) {
			return "sort="+sort;
		}
		else {
			return "sort="+sort;
		}
	}
	
	
	
}
