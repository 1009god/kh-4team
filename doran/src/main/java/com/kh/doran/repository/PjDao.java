package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjDto;


public interface PjDao {
	//C
	
	//R
	PjDto selectOne(int pjNo); //프로젝트 1개의 정보 보기
	List<PjDto> selectList(); //상품 목록
	List<PjDto> selectList(String type, String keyword); //상품 목록,검색
	
	//U
	//D
}
