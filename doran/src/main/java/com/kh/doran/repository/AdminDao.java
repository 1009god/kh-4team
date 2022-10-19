package com.kh.doran.repository;

import com.kh.doran.entity.AdminDto;

public interface AdminDao {
		void insert(AdminDto adminDto);
		AdminDto selectOne(String adminEmail);
		boolean updateLoginTime(String adminEmail);
}
