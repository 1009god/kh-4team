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

import com.kh.doran.entity.PjDto;
import com.kh.doran.entity.PjFileDto;
import com.kh.doran.vo.PjListSearchVO;

@Repository
public class PjFileDaoImpl implements PjFileDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//매퍼
	private RowMapper<PjFileDto> mapper=new RowMapper<PjFileDto>(){

		@Override
		public PjFileDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return PjFileDto.builder()
									.pjFilePjNo(rs.getInt("pj_file_pj_no"))
									.pjFileNo(rs.getInt("pj_file_no"))
									.pjFileClassify(rs.getString("pj_file_classify"))
									.build();
					
		}
};
	
	//추출기
	private ResultSetExtractor<PjFileDto> extractor=new ResultSetExtractor<PjFileDto>() {
	
		@Override
		public PjFileDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return PjFileDto.builder()
						.pjFilePjNo(rs.getInt("pj_file_pj_no"))
						.pjFileNo(rs.getInt("pj_file_no"))
						.pjFileClassify(rs.getString("pj_file_classify"))
						.build();
			}
			else {
				return null;
			}
		}
	};
	
	@Override
	public List<PjFileDto> pjFileList(int pjFilePjNo) {
		String sql="select*from pj_file where pj_file_pj_no=? and pj_file_classify='대표'";
		Object[] param= {pjFilePjNo};
		return jdbcTemplate.query(sql, mapper, param);
	}
	


	

	
	@Override
	public PjFileDto selectOne(int pjFilePjNo) {
		String sql="select*from pj_file where pj_file_pj_no=? and pj_file_classify='소개'";
		Object[] param= {pjFilePjNo};
		return jdbcTemplate.query(sql, extractor, param);
	}
	
}
