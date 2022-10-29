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

import com.kh.doran.entity.FaqDto;

@Repository
public class FaqDaoImpl implements FaqDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<FaqDto> mapper = new RowMapper<FaqDto>() {
		@Override
		public FaqDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			FaqDto dto = new FaqDto();
			dto.setFaqNo(rs.getInt("faq_no"));
			dto.setFaqAdminNo(rs.getInt("faq_admin_no"));
			dto.setFaqType(rs.getString("faq_type"));
			dto.setFaqTitle(rs.getString("faq_title"));
			dto.setFaqContent(rs.getString("faq_content"));
			return dto;
		}
	};
	@Override
	public List<FaqDto> selectList() {
		String sql = "select * from faq order by faq_no asc";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<FaqDto> selectList(String type, String keyword) {
		String sql = "select * from faq where instr(#1,?)>0 order by #1 asc";
		sql=sql.replace("#1", type);
		Object[]param= {keyword};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	private ResultSetExtractor<FaqDto> extractor = new ResultSetExtractor<FaqDto>() {
		@Override
		public FaqDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				FaqDto dto = new FaqDto();
				dto.setFaqNo(rs.getInt("faq_no"));
				dto.setFaqAdminNo(rs.getInt("faq_admin_no"));
				dto.setFaqType(rs.getString("faq_type"));
				dto.setFaqTitle(rs.getString("faq_title"));
				dto.setFaqContent(rs.getString("faq_content"));
				return dto;
			}
			else {
				return null;
			}
		}
	};

	@Override
	public FaqDto selectOne(int faqNo) {
		String sql = "select * from faq where faq_no=?";
		Object[]param= {faqNo};
		return jdbcTemplate.query(sql, extractor,param);
	}

	@Override
	public void insert(FaqDto faqDto) {
		String sql="insert into faq(faq_no,faq_type,faq_title,faq_content)"
				+ "values(faq_seq.nextval,?,?,?)";
		Object[]param= {faqDto.getFaqType(),faqDto.getFaqTitle(),
				faqDto.getFaqContent()};
		jdbcTemplate.update(sql,param);
		
	}

	@Override
	public boolean update(FaqDto faqDto) {
		String sql="update faq "
				+ "set faq_type=?,faq_title=?,faq_content=?"
				+ " where faq_no=?";
		Object[]param= {
				faqDto.getFaqType(),faqDto.getFaqTitle(),
				faqDto.getFaqContent(),faqDto.getFaqNo()};
		return jdbcTemplate.update(sql,param)>0;
	}

	@Override
	public boolean delete(int faqNo) {
		String sql = "delete faq where faq_no=?";
		Object[]param = {faqNo};
		return jdbcTemplate.update(sql,param)>0;
	}

}
