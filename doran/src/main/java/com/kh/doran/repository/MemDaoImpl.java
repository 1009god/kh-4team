package com.kh.doran.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.MemDto;


@Repository
public class MemDaoImpl implements MemDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	private RowMapper<MemDto> mapper = new RowMapper<MemDto>() {
//		@Override
//		public MemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//			MemDto dto = new MemDto();
//			dto.setMemNo(rs.getInt("memNo"));
//			dto.setMemEmail(rs.getString("memEmail"));
//			dto.setMemPw(rs.getString("memPw"));
//			dto.setMemNick(rs.getString("memNick"));
//		}
//	} 미완
			
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
	
	
	
}
