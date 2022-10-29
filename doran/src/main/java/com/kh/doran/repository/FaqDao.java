package com.kh.doran.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kh.doran.entity.FaqDto;

@Repository
public interface FaqDao {

	void insert(FaqDto faqDto);
	
	boolean update(FaqDto faqDto); 
	
	boolean delete(int faqNo);
	
	
	List<FaqDto>selectList();
	List<FaqDto>selectList(String type,String keyword);
	
	FaqDto selectOne(int faqNo);
	
	
	
}
