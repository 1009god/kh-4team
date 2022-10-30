package com.kh.doran.repository;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.kh.doran.entity.SellerQDto;
import com.kh.doran.vo.BoardListSearchVO;

public interface SellerQDao {
	void insert(SellerQDto sellerQDto); //insert 문의 작성
	
	List<SellerQDto> selectList(@RequestParam int sellerQPjNo);  
	// 해당 프로젝트번호가 들어간 글만 뽑아와야 해서 일괄적으로 다 뽑는 목록이랑은 다를것임 
	
	//SellerQDto selectOne(int sellerQNo);
	
	//boolean delete(int sellerQNo);
	
	
	// 검색과 목록의 총 데이터 갯수를 구하는 메소드 (마지막 페이지)
	//	 int count(BoardListSearchVO vo);	// 해당 pj_no에 따른 게시글 개수를 구하는 메소드
	//	 int listCount(BoardListSearchVO vo);   
	
}
