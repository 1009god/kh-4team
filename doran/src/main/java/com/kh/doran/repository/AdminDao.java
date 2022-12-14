package com.kh.doran.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kh.doran.entity.AdminDto;
import com.kh.doran.entity.MemDto;
import com.kh.doran.vo.AdminMemListVO;
import com.kh.doran.vo.BoardListSearchVO;
import com.kh.doran.vo.MemListSearchVO;
import com.kh.doran.vo.PjListSearchVO;

@Repository
public interface AdminDao {
		
		//관리자 테이블
		AdminDto selectOne(int adminNo);
		AdminDto selectOne(String adminEmail);
		void insert(AdminDto adminDto);
		boolean updateLoginTime(int adminNo);
		
		//회원 테이블 
		List<MemDto> selectList();
		
		List<AdminMemListVO> selectList(MemListSearchVO vo);
		List<AdminMemListVO> list(MemListSearchVO vo);
		List<AdminMemListVO> search(MemListSearchVO vo);
		
		//수정,상세
		boolean update(MemDto memDto);
		MemDto selectOne1(int memNo);
		boolean delete(int memNo);
		
		int count(MemListSearchVO vo);
		int searchCount(MemListSearchVO vo);
		int listCount(MemListSearchVO vo);
		
		
		
		
		//프로젝트 테이블
		
		
		//회원 테이블
		
		
		
		//게시판 테이블

	
		
}
