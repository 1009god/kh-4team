package com.kh.doran.repository;

import com.kh.doran.entity.PjDto;


public interface PjDao {
	
	PjDto selectOne(int pjNo); //프로젝트 1개의 정보 보기

}
