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
	
	private String sort;
	
	@ToString.Include
	public boolean isPopular() {
		if(sort!=null) {
			return sort.equals("pj_likes_number");			
		}
		else {
			return false;
		}
	}
	
	public boolean isImminent() {
		if(sort!=null) {
			return sort.equals("pj_funding_end_date-sysdate");
		}
		else{
			return false;
		}
	}
	
	public boolean isLatest() {
		if(sort!=null) {
			return sort.equals("pj_no");
		}
		else{
			return false;
		}
	}
	
	private String category;
	
	public boolean isCategory() {
		return category!=null;
	}
	
	//현재 페이지 번호(없을 경우 p=1로 설정)
	private int p = 1;
	private int size = 12;
	
	@ToString.Include
	public int startRow() {
		return endRow()-(size-1);
	}
	
	@ToString.Include
	public int endRow() {
		return p * size;
	}
	
	// 총 게시글 수
	private int count;
	
	//화면에 표시할 블럭 개수
	private int blockSize = 5;
	
	@ToString.Include
	public int pageCount() {
		return(count+size-1)/size;
	}
	
	@ToString.Include
	public int startBlock() {
		return (p-1) / blockSize * blockSize + 1;
	}
	
	@ToString.Include
	public int endBlock() {
		int value = startBlock() + blockSize-1;
		return Math.min(value, lastBlock());
	}
	
	public int prevBlock() {
		return startBlock()-1;
	}
	
	public int nextBlock() {
		return endBlock()+1;
	}
	
	public int firstBlock() {
		return 1;
	}
	
	public int lastBlock() {
		return pageCount();
	}
	
	public boolean isFirst() {
		return p == 1;
	}
	
	public boolean isLast() {
		return endBlock()==lastBlock();
	}
	
	public boolean hasPrev() {
		return startBlock() > 1;
	}
	
	public boolean hasNext() {
		return endBlock() < lastBlock();
	}
	
	//검색이나 크기 유지
	public String parameter() {
		if(isSearch()) {
			return "size="+size+"&type="+type+"&keyword="+keyword;
		}
		else {
			return "size="+size;
		}
	}
	
	//정렬 기준
	public String sortBy() {
		if(isPopular()) {
			return "size="+size+"&sort="+sort;
		}
		if(isImminent()) {
			return "size="+size+"&sort="+sort;
		}
		if(isLatest()) {
			return "size="+size+"&sort="+sort;
		}
		else {
			return "size="+size;
		}
	}
	
	//카테고리별 크기 유지
	public String categoryBy() {
		if(isCategory()) {
			return "size="+size+"&category="+category;
		}
		else {
			return "size="+size;
		}
	}



}