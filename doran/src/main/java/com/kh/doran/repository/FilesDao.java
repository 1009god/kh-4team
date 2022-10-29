package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.FilesDto;
import com.kh.doran.vo.profileImgVO;

public interface FilesDao {
	int sequence();
	void insert(FilesDto filesDto); //삽입 
	List<FilesDto> selectList(); 
	FilesDto selectOne(int filesNo);//프라이머리키로 조회
	boolean delete(int filesNo); //프라이머리키로 삭제
	
	//mem프로필이미지 파일 저장
	void connectFiles(int filesNo, int memNo);
	//프로필 이미지 첨부파일 list(profile_img_view 조회)
	List<profileImgVO> profileImgList(int memNo);
	
	
	//프로젝트 신청시 첨부파일(이미지) 저장
	//void connectFiles(int filesNo, int pjNo, String pjFileClassify);
	
	//셀러 첨부파일 이미지 저장
	void connectSellerFiles(int filesNo, int sellerMemNo);
	
	//공지사항 게시판 첨부파일 저장 (notice_file_view)
	List<FilesDto> selectNoticeFileList(int noticeFileNoticeNo);
	
	//공지사항 게시판 첨부파일 저장 (notice_file_view)
	List<FilesDto> selectBoardFileList(int boardImgPostNo);
}
