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
	private int blockSize = 10;
	
	@ToString.Include
	public int pageCount() {
		return(count+size-1)/size;
	}
	
	@ToString.Include
	public int startBlock() {
		return endBlock()-(blockSize-1);
	}
	
	@ToString.Include
	public int endBlock() {
		return(p+blockSize-1) / blockSize*blockSize;
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

}