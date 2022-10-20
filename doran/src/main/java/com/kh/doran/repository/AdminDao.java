package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.AdminDto;
import com.kh.doran.entity.MemDto;

public interface AdminDao {
		void insert(AdminDto adminDto);
		AdminDto selectOne(String adminEmail);
		boolean updateLoginTime(String adminEmail);

	
		
}
