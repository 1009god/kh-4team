package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.LikesDto;
import com.kh.doran.vo.likeVO;

public interface LikesDao {
	void insert(LikesDto dto);//좋아요 등록
	void delete(LikesDto dto);//좋아요 취소
	boolean check(LikesDto dto);//이 프로젝트에 좋아요 했는지 여부 확인
	int count(int pjNo);//특정 프로젝트에 좋아요가 몇 개인지 세기
	
	void refresh(int pjNo);
	
	List<likeVO> likeList(int sellerMemNo);
}
