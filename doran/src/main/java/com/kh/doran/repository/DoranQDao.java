package com.kh.doran.repository;

import java.util.List;


import com.kh.doran.entity.DoranQDto;
import com.kh.doran.vo.DoranQListSearchVO;

public interface DoranQDao {
	void insert(DoranQDto doranQDto);
	List<DoranQDto> selectList(); 
	/*
	 * int insert2(DoranQDto doranQDto); //int 인 이유, 번호를 알아야 하니까 최근 게시물 올리면 디테일로 가게
	 * 해 주는
	 */	
	
//	//통합 검색 메소드 (목록 + 검색) 셀렉트 리스트 만들고 리스트와 서치에 쪼개지게
//	List<DoranQDto> selectList(DoranQListSearchVO vo);
//	List<DoranQDto> list(DoranQListSearchVO vo);
//	List<DoranQDto> search(DoranQListSearchVO vo);
//	
//	//검색과 목록의 총 데이터 갯수를 구하는 메소드 (마지막 페이지)
//	int count(DoranQListSearchVO vo);
//	int searchCount(DoranQListSearchVO vo);
//	int listCount(DoranQListSearchVO vo);
	
	
	DoranQDto selectOne(int doranQNo);

	boolean update(DoranQDto doranQDto);
	boolean delete(int doranQNo);
	

	

}

