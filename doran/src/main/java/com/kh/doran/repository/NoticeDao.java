package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.NoticeDto;
import com.kh.doran.vo.NoticeListSearchVO;


public interface NoticeDao {
	
	void insert(NoticeDto noticeDto); //입력
	List<NoticeDto> selectList(); //사용 x
	NoticeDto selectOne(int noticeNo);
	
	int insert2(NoticeDto NoticeDto); //int 인 이유, 번호를 알아야 하니까 최근 게시물 올리면 디테일로 가게 해 주는

	//통합 검색 메소드 (목록 + 검색) 셀렉트 리스트 만들고 리스트와 서치에 쪼개지게
	List<NoticeDto> selectList(NoticeListSearchVO vo); //검색, 목록
	List<NoticeDto> list(NoticeListSearchVO vo);
	List<NoticeDto> search(NoticeListSearchVO vo);

	//검색과 목록의 총 데이터 갯수를 구하는 메소드 (마지막 페이지)
	int count(NoticeListSearchVO vo);
	int searchCount(NoticeListSearchVO vo);
	int listCount(NoticeListSearchVO vo);
	
	
	boolean update(NoticeDto noticeDto);
	boolean delete(int noticeNo);
	
	void clear();

}
