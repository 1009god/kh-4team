package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjFileDto;
import com.kh.doran.vo.PjListSearchVO;

public interface PjFileDao {

	List<PjFileDto> pjFileList(int pjFilePjNo);//개별 프로젝트의 대표 이미지들을 리스트로 가져옴
	
	PjFileDto selectOne(int pjFilePjNo);//개별 프로젝트의 소개 이미지를 가져옴

	List<PjFileDto> pjFileList(PjListSearchVO vo);
	
//	List<PjFileDto> pjFileList();
}
