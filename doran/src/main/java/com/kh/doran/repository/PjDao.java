package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjDto;
import com.kh.doran.vo.PjListSearchVO;


public interface PjDao {
	//C
	void insert(PjDto pjDto);
	//R
	PjDto selectOne(int pjNo); //프로젝트 1개의 정보 보기

	//통합 검색 메소드(목록+검색)
	List<PjDto> selectList(PjListSearchVO vo); 
	List<PjDto> list(PjListSearchVO vo); 
	List<PjDto> search(PjListSearchVO vo);
	
	//인기순
	List<PjDto> popular(PjListSearchVO vo);
	
	//마감임박순
	List<PjDto> imminent(PjListSearchVO vo);
	
	//최신순
	List<PjDto> latest(PjListSearchVO vo);
	
	//카테고리별 정렬
	List<PjDto> category(PjListSearchVO vo);
	
	//검색과 목록의 총 데이터 갯수를 구하는 메소드(마지막 페이지 번호를 구하기 위해서 사용하는 메소드)
	int count(PjListSearchVO vo);
	int searchCount(PjListSearchVO vo);
	int listCount(PjListSearchVO vo);
	
	//카테고리별 데이터 갯수
	int categoryCount(PjListSearchVO vo);

	
	// 인기순
//	List<PjDto> popular(PjSortVO vo);
//	
//	int count(PjSortVO vo);
	
	
	//U
	//D
}