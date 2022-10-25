package com.kh.doran.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.MemDto;


@Repository
public class MemDaoImpl implements MemDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private ResultSetExtractor<MemDto> extractor = new ResultSetExtractor<MemDto>() {
		
		@Override
		public MemDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				MemDto dto = new MemDto();
				dto.setMemNo(rs.getInt("mem_no"));
				dto.setMemEmail(rs.getString("mem_email"));
				dto.setMemPw(rs.getString("mem_pw"));
				dto.setMemNick(rs.getString("mem_nick"));
				dto.setMemTel(rs.getString("mem_tel"));
				dto.setMemJoinDate(rs.getDate("mem_join_date"));
				dto.setMemRoute(rs.getString("mem_route"));	
				return dto;
			}
			else {
				return null;
			}
		}
	};
			
	public void insert(MemDto memDto) {
		String sql = "insert into mem( "
				+ "mem_no, mem_email, mem_pw, mem_nick, "
				+ "mem_tel, mem_join_date, mem_route) "
				+ "values(mem_seq.nextval, ?, ?, ?, ?, sysdate, ?)";
		
		Object[] param = {
				memDto.getMemEmail(), memDto.getMemPw(),
				memDto.getMemNick(), memDto.getMemTel(), 
				memDto.getMemRoute()
		};
		jdbcTemplate.update(sql, param);
	}

	@Override
	public MemDto selectOne(int memNo) {
		String sql = "select * from mem where mem_no = ?";
		Object[] param = {memNo};
		return jdbcTemplate.query(sql, extractor, param);
	}
	
	@Override
	public MemDto selectOne(String memEmail) {
		String sql = "select * from mem where mem_email = ?";
		Object[] param = {memEmail};
		return jdbcTemplate.query(sql, extractor, param);
	}

	

	@Override
	public boolean profileUpdate(MemDto memDto) { //구버전
		String sql = "update mem set mem_nick=?, mem_tel=? where mem_no = ?";
		Object[] param = {
				memDto.getMemNick(), memDto.getMemTel(), memDto.getMemNo()
		};
		return jdbcTemplate.update(sql, param) > 0;
	}

	@Override
	public boolean editProfile(MemDto dto) {
		// 세션을 이용한 프로필 수정
		String sql = "update mem set mem_nick = ? where mem_no= ?";		
		Object[] param = {dto.getMemNick(), dto.getMemNo()};
		//세션에서 no를 가져와야함
		return jdbcTemplate.update(sql, param) > 0;
	}
	
	
//	@Override 로그인 시간 구현 필요하게 되면
//	public boolean updateLoginTime(int memNo) { //로그인 시간 업데이트
//		String sql = "update mem "
//							+ "set mem_login=sysdate "
//							+ "where mem_no=?";
//		Object[] param = {memNo};
//		return jdbcTemplate.update(sql, param) > 0;
//	}
	
	
	//수정-계정 delete 
	@Override
	public boolean delete(String memEmail) {
		String sql = "delete mem where mem_email = ?";
		Object[] param = {memEmail};
		return jdbcTemplate.update(sql,param) > 0;
	}

	//수정-계정account수정(비밀번호, 전화번호)
	@Override
	public boolean editAccountPw(MemDto dto) {
		// 세션을 이용한 프로필 수정
				String sql = "update mem set mem_pw = ? where mem_no= ?";		
				Object[] param = {dto.getMemPw(), dto.getMemNo()};
				//세션에서 no를 가져와야함
				return jdbcTemplate.update(sql, param) > 0;		
	}

	@Override
	public boolean editAccountTel(MemDto dto) {
		// 세션을 이용한 프로필 수정
		String sql = "update mem set mem_tel = ? where mem_no = ?";
		Object[] param = {dto.getMemTel(), dto.getMemNo()};
		return jdbcTemplate.update(sql, param) > 0;	
	}

	
	
}


	