package com.kh.doran.repository;

import java.util.List;


import com.kh.doran.entity.DoranQDto;
import com.kh.doran.vo.DoranQListSearchVO;

public interface DoranQDao {
	int sequence();
	void insert(DoranQDto doranQDto);
	void adminInsert(DoranQDto doranQDto);
	
	List<DoranQDto> selectList(DoranQListSearchVO vo);
	List<DoranQDto> list(DoranQListSearchVO vo);
	
	DoranQDto selectOne(int doranQNo);


	boolean delete(int doranQNo);
	
	
	/*
	 * int insert2(DoranQDto doranQDto); //int 인 이유, 번호를 알아야 하니까 최근 게시물 올리면 디테일로 가게
	 * 해 주는
	 */	
	
	

	void clear();
	int listCount(DoranQListSearchVO vo);
	List<DoranQDto> search(DoranQListSearchVO vo);


	
//	//검색과 목록의 총 데이터 갯수를 구하는 메소드 (마지막 페이지)
//	int count(DoranQListSearchVO vo);
//	int searchCount(DoranQListSearchVO vo);
//	int listCount(DoranQListSearchVO vo);
	
	


	

}

