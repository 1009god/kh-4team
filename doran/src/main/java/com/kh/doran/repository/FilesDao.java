package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.FilesDto;

public interface FilesDao {
	int sequence();
	void insert(FilesDto filesDto);
	List<FilesDto> selectList();
	FilesDto selectOne(int filesNo);
	boolean delete(int filesNo);
}
