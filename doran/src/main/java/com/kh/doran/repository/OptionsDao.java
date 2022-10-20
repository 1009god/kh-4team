package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.OptionsDto;

public interface OptionsDao {

	List<OptionsDto> selectList(int optionsPjNo);//한 개의 프로젝트가 가진 모든 옵션
	OptionsDto selectOne(int optionsNo);//한 개의 프로젝트가 가진 옵션들 중 하나를 선택해서 주문창으로 넘기기 위한 메소드
	
}
