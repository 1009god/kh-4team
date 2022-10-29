package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.BoardDto;
import com.kh.doran.vo.BoardDetailVO;
import com.kh.doran.vo.BoardListSearchVO;
import com.kh.doran.vo.BoardListVO;

public interface BoardDao {
	void insert(BoardDto boardDto);
	int insert2(BoardDto boardDto); //int 인 이유, 번호를 알아야 하니까 최근 게시물 올리면 디테일로 가게 해 주는
	
	List<BoardDto> selectList(); //더이상 사용 x
	
	//통합 검색 메소드 (목록 + 검색) 셀렉트 리스트 만들고 리스트와 서치에 쪼개지게
	List<BoardListVO> selectList(BoardListSearchVO vo);
	List<BoardListVO> list(BoardListSearchVO vo);
	List<BoardListVO> search(BoardListSearchVO vo);
	
	//검색과 목록의 총 데이터 갯수를 구하는 메소드 (마지막 페이지)
	int count(BoardListSearchVO vo);
	int searchCount(BoardListSearchVO vo);
	int listCount(BoardListSearchVO vo);
	
	BoardDetailVO selectOne(int boardPostNo);
	BoardDetailVO read(int boardPostNo); //조회수 증가까지
	
	boolean update(BoardDto boardDto);
	boolean delete(int boardPostNo);
	
	void clear();
	boolean updateReadcount(int boardPostNo);
	
	//첨부파일 관련 기능
	void connectFiles(int boardImgPostNo, int boardImgNo);

}
