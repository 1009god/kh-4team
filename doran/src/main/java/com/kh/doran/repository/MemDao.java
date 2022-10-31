package com.kh.doran.repository;

import com.kh.doran.entity.MemDto;

public interface MemDao {
	void insert(MemDto memDto);
	MemDto selectOne(int memNo);
	MemDto selectOne(String memEmail);
	MemDto findByNickname(String memNick);

	
	boolean profileUpdate(MemDto dto);  //구버전
	boolean editProfile(MemDto dto); //신버전
	
	boolean delete(String memEmail);
	
	boolean editAccountPw(MemDto dto); // 수정-계정수정
	boolean editAccountTel(MemDto dto); // 수정-계정수정
	

	MemDto findEmail(String memTel); //아이디 찾기
	MemDto findPw(String memEmail, String memTel); //아이디 찾기
	


//	MemDto findByPw(int memNo, String memPw);  //비동기 pw 일치불일치 메소드

	

	
	


}
