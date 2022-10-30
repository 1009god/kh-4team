package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.NoticeDto;
import com.kh.doran.vo.NoticeListSearchVO;


public interface NoticeDao {
	void insert(NoticeDto noticeDto);
	int sequence();
	int insert2(NoticeDto noticeDto);

	List<NoticeDto> selectList();//더이상 사용 x

	//통합 검색 메소드(목록+검색)
	List<NoticeDto> selectList(NoticeListSearchVO vo);
	List<NoticeDto> list(NoticeListSearchVO vo);
	List<NoticeDto> search(NoticeListSearchVO vo);
	
	//검색과 목록의 총 데이터 갯수를 구하는 메소드 (마지막 페이지)
	int count(NoticeListSearchVO vo);
	int searchCount(NoticeListSearchVO vo);
	int listCount(NoticeListSearchVO vo);
	
	NoticeDto selectOne(int noticeNo);

	boolean update(NoticeDto noticeDto);
	boolean delete(int boardNo);
	
	void clear();
	
	//첨부파일 관련 기능
	void connectFiles(int noticeFileNoticeNo, int noticeFileNo);
}
