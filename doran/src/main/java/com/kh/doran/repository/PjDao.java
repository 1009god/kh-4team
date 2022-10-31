package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjDto;
import com.kh.doran.vo.OrderCountVO;
import com.kh.doran.vo.OrdersCalVO;
import com.kh.doran.vo.PjFileVO;
import com.kh.doran.vo.PjListSearchVO;



public interface PjDao {
	//C
	
	void insert(PjDto pjDto);
	int sequence();
	//R
	PjDto selectOne(int pjNo); //프로젝트 1개의 정보 보기
	
	int orderCount(OrderCountVO vo);//로그인한 사용자가 이 프로젝트를 이미 후원한 상태인지 확인(boolean이 맞는거같긴함)

	int orderCountAll(int pjNo);//이 프로젝트의 후원자는 몇 명인지
	
	float dateCount(int pjNo);
	
	
	
	
	
	
	
	
	//통합 검색 메소드(목록+검색)
	List<PjListSearchVO> selectList(PjListSearchVO vo); 
	List<PjListSearchVO> list(PjListSearchVO vo); 
	List<PjListSearchVO> search(PjListSearchVO vo);
	
	//인기순
	List<PjListSearchVO> popular(PjListSearchVO vo);
	
	//마감임박순
	List<PjListSearchVO> imminent(PjListSearchVO vo);
	
	//최신순
	List<PjListSearchVO> latest(PjListSearchVO vo);
	
	//카테고리별 정렬
	List<PjListSearchVO> category(PjListSearchVO vo);
	
	//펀딩예정 
	List<PjListSearchVO> prelaunching(PjListSearchVO vo);
	
	//펀딩중 
	List<PjListSearchVO> ongoing(PjListSearchVO vo);
	
	//펀딩 마감
	List<PjListSearchVO> finishing(PjListSearchVO vo);
	
	//검색과 목록의 총 데이터 갯수를 구하는 메소드(마지막 페이지 번호를 구하기 위해서 사용하는 메소드)
	int count(PjListSearchVO vo);
	int searchCount(PjListSearchVO vo);
	int listCount(PjListSearchVO vo);
	
	//카테고리별 데이터 갯수
	int categoryCount(PjListSearchVO vo);
	

	
	//주문금액, 달성률 계산(개별)
	OrdersCalVO calVo(int pjNo);

	//펀딩예정 데이터 갯수
	int prelaunchingCount(PjListSearchVO vo);
	
	//펀딩중 데이터 갯수
	int ongoingCount(PjListSearchVO vo);
	
	//펀딩마감 데이터 갯수
	int finishingCount(PjListSearchVO vo);
	


	//마이페이지- 후원한 프로젝트에 쓰임	
	//	List<SupportPjVO> supportList(); 

	
	

	//U
	//D
}