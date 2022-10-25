package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.AdminDto;
import com.kh.doran.entity.MemDto;
import com.kh.doran.vo.MemListVO;

public interface AdminDao {
		
		//관리자 테이블
		AdminDto selectOne(int adminNo);
		AdminDto selectOne(String adminEmail);
		void insert(AdminDto adminDto);
		boolean updateLoginTime(int adminNo);
		
		//회원 테이블 
		List<MemDto> selectList();
		List<MemDto> selectList(String type, String keyword);
		
		//수정,상세
		boolean update(MemDto memDto);
		MemDto selectOne1(int memNo);
		boolean delete(int memNo);
		
		
		//프로젝트 테이블
		
		
		//회원 테이블
		
		
		
		//게시판 테이블

	
		
}
