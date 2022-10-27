package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.MemDto;
import com.kh.doran.entity.PjDto;
import com.kh.doran.vo.SupportPjVO;

public interface MemDao {
	void insert(MemDto memDto);
	MemDto selectOne(int memNo);
	MemDto selectOne(String memEmail);

	
	boolean profileUpdate(MemDto dto);  //구버전
	boolean editProfile(MemDto dto); //신버전
	
	boolean delete(String memEmail);
	
//	boolean updateLoginTime(int memNo); 로그인 시간 구헌
	
	boolean editAccountPw(MemDto dto); // 수정-계정수정
	boolean editAccountTel(MemDto dto); // 수정-계정수정
	

	
	


}
