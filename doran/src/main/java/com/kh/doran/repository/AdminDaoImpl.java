package com.kh.doran.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.AdminDto;
import com.kh.doran.entity.MemDto;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MemDao memDao;

	@Autowired
	private PjDao pjDao;

	
private ResultSetExtractor<AdminDto> extractor = new ResultSetExtractor<AdminDto>() {
		
		@Override
		public AdminDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				AdminDto dto = new AdminDto();
				dto.setAdminNo(rs.getInt("admin_no"));
				dto.setAdminEmail(rs.getString("admin_eamil"));
				dto.setAdminPw(rs.getString("admin_pw"));
				dto.setAdminNick(rs.getString("admin_nick"));
				
				return dto;
			}
			else {
				return null;
			}
		}
	};
	@Override
	public AdminDto selectOne(String adminEmail) {
		String sql="select*from mem";
		Object[]param= {adminEmail};
		return jdbcTemplate.query(sql, extractor,param);
	}
	
	@Override
	public void insert(AdminDto adminDto) {
		String sql = "INSERT INTO ADMIN(ADMIN_NO,ADMIN_EMAIL,ADMIN_PW,ADMIN_NICK)" + "VALUES(ADMIN_SEQ.NEXTVAL,?,?,?)";
		Object[] param = { adminDto.getAdminEmail(), adminDto.getAdminPw(), adminDto.getAdminNick() };
		jdbcTemplate.update(sql, param);
	}
	
	@Override
	public boolean updateLoginTime(String adminEmail) {
		String sql = "UPDATE ADMIN " + "SET ADMIN_LOGIN=SYSDATE " + "WHERE ADMIN_EMAIL=?";
		Object[] param = { adminEmail };
		return jdbcTemplate.update(sql, param) > 0;
	}



	private RowMapper<MemDto> mapper = new RowMapper<MemDto>() {
		@Override
		public MemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
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
	};
	
	@Override
	public List<MemDto>selectList(){
		String sql = "select * from mem";
		return jdbcTemplate.query(sql,mapper);
	}
	
	@Override
	public List<MemDto>selectList(String type,String keyword){
		String sql = "select * from mem where instr(#1,?)>0 order by #1 asc";
		Object[]param= {keyword};
		return jdbcTemplate.query(sql, mapper,param);
	}

	@Override
	public boolean update(MemDto memDto) {
		String sql="update mem"
				+ "set"
				+ "mem_nick=?"
				+ "where mem_no=?";
		Object[]param= {memDto.getMemNick(),memDto.getMemNo()};
		return jdbcTemplate.update(sql,param)>0;
	}
	
}
