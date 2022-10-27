package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.FilesDto;

public interface FilesDao {
	int sequence();
	void insert(FilesDto filesDto); //삽입 
	List<FilesDto> selectList(); 
	FilesDto selectOne(int filesNo);//프라이머리키로 조회
	boolean delete(int filesNo); //프라이머리키로 삭제
	
	//mem프로필이미지 파일 저장
	void connectFiles(int filesNo, int memNo);
	//프로필 이미지 첨부파일 list(profile_img_view 조회)
	List<FilesDto> profileImgList(int memNo);
}
