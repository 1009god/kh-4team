package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjDto;
import com.kh.doran.vo.PjListSearchVO;


public interface PjDao {
	//C
	
	//R
	PjDto selectOne(int pjNo); //프로젝트 1개의 정보 보기
	List<PjDto> selectList(); //상품 목록
	List<PjDto> selectList(PjListSearchVO vo); //상품 목록,검색
	
	//U
	//D
}