package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.AdminDto;
import com.kh.doran.entity.MemDto;
import com.kh.doran.vo.MemListVO;

public interface AdminDao {
		
		//관리자 테이블
		AdminDto selectOne(String adminEmail);
		void insert(AdminDto adminDto);
		boolean updateLoginTime(String adminEmail);
		
		//회원 테이블 
		List<MemDto> selectList();
		List<MemDto> selectList(String type, String keyword);
		
//		List<MemListVO>selectList(MemListVO vo);
//		int count(MemListVO vo);
		
		boolean update(MemDto memDto);
		
		
		//프로젝트 테이블
		
		
		//회원 테이블
		
		
		
		//게시판 테이블

	
		
}
