package com.kh.doran.vo;

import lombok.Data;
import lombok.ToString;

@Data
public class PjListSearchVO2 {
	//검색 분류와 검색어
	private String type, keyword;
	
	@ToString.Include
	public boolean isSearch() {
		return type!=null && keyword!=null;
	}
	
	public boolean isCategory() {
		return type!=null && keyword==null;
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
		else if(isCategory()) {
			return "size="+size+"&type="+type;
		}
		else {
			return "size="+size;
		}
	}
	

}