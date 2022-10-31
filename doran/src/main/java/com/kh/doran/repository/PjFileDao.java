package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.PjFileDto;

public interface PjFileDao {

	List<PjFileDto> pjFileList(int pjFilePjNo);
}
