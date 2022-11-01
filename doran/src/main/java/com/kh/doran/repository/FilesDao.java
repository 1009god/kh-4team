package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.FilesDto;
import com.kh.doran.vo.PjFileVO;
import com.kh.doran.vo.SellerFileVO;
import com.kh.doran.vo.SupportDetailImgVO;
import com.kh.doran.vo.profileImgVO;

public interface FilesDao {
	
	int sequence();  //시퀀스번호 미리 생성
	
	void insert(FilesDto filesDto); //삽입 
	List<FilesDto> selectList(); 
	FilesDto selectOne(int filesNo);//프라이머리키로 조회
	boolean delete(int filesNo); //프라이머리키로 삭제
	
	//mem프로필이미지 파일 저장
	void connectFiles(int filesNo, int memNo);
	//프로필 이미지 첨부파일 list(profile_img_view 조회)
	List<profileImgVO> profileImgList(int memNo);
	
	
	//프로젝트 신청시 첨부파일(이미지) 저장
	//컬럼3개인 테이블
	//List<FilesDto> connectPjFiles(int pjNo, int filesNo);
	void connectPjFiles(int pjNo, int filesNo );
	List<PjFileVO>pjfile(int sellerMemNo);
	
	//셀러 첨부파일 list(seller_file_view 조회)
	List<SellerFileVO> sellerFileList(int sellerMemNo);
	
	
	
	//셀러 첨부파일 이미지 저장
	void connectSellerFiles( int sellerMemNo,int filesNo);
	
	//공지사항 게시판 첨부파일 저장 (notice_file_view)
	List<FilesDto> selectNoticeFileList(int noticeFileNoticeNo);
	
	//일반 게시판 (도란도란) 첨부파일 저장 (notice_file_view)
	List<FilesDto> selectBoardFileList(int boardImgPostNo);

	//강사추가 메소드
	void connectPjFiles(int pjNo, int filesNo, String type);
	
	//supported 내역 detail 썸네일 대표 이미지
	List<SupportDetailImgVO> supportDetailImgList(int ordersNo);
	
	
}
