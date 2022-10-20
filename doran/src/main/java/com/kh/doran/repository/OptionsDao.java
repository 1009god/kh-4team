package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.OptionsDto;

public interface OptionsDao {

	public List<OptionsDto> selectList(int optionsPjNo);
}
