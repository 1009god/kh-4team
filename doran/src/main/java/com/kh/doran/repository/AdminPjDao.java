package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjDto;
import com.kh.doran.vo.AdminpjListVO;
import com.kh.doran.vo.BoardListSearchVO;
import com.kh.doran.vo.BoardListVO;
import com.kh.doran.vo.PjListSearchVO;

public interface AdminPjDao {

	
	
	List<PjDto> selectList();
	List<AdminpjListVO> selectList(PjListSearchVO vo);
	List<AdminpjListVO> list(PjListSearchVO vo);
	List<AdminpjListVO> search(PjListSearchVO vo);
	
	//검색과 목록의 총 데이터 갯수를 구하는 메소드 (마지막 페이지)
	int count(PjListSearchVO vo);
	int searchCount(PjListSearchVO vo);
	int listCount(PjListSearchVO vo);
	
	PjDto selectOne(int pjNo);
	boolean delete(int pjNo);
}
