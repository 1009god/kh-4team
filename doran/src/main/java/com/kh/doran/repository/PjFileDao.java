package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjFileDto;
import com.kh.doran.vo.PjListSearchVO;

public interface PjFileDao {

	List<PjFileDto> pjFileList(int pjFilePjNo);

	List<PjFileDto> pjFileList(PjListSearchVO vo);
	
	List<PjFileDto> pjFileList();
}
