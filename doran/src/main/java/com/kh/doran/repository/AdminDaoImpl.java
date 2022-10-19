package com.kh.doran.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.AdminDto;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(AdminDto adminDto) {
		String sql = "INSERT INTO ADMIN(ADMIN_NO,ADMIN_EMAIL,ADMIN_PW,ADMIN_NICK)"
				+ "VALUES(ADMIN_SEQ.NEXTVAL,?,?,?)";
		Object[]param= {adminDto.getAdminEmail(),adminDto.getAdminPw(),adminDto.getAdminNick()};
		jdbcTemplate.update(sql,param);
	}

	@Override
	public AdminDto selectOne(String adminEmail) {
		
		return null;
	}

//	@Override
//	public boolean updateLoginTime(String memberId) {
//		String sql = "UPDATE ADMIN "
//						+ "SET ADMIN_LOGIN=SYSDATE "
//						+ "WHERE ADMIN_EMAIL=?";
//		Object[] param = {adminEmail};
//		return jdbcTemplate.update(sql, param) > 0;
//	}
}
