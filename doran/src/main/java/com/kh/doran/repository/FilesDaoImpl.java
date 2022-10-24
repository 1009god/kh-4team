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

import com.kh.doran.entity.FilesDto;

@Repository
public class FilesDaoImpl implements FilesDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<FilesDto> mapper = new RowMapper<>(){

		@Override
		public FilesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return FilesDto.builder()
					.filesNo(rs.getInt("FILES_NO"))
					.filesUploadname(rs.getString("FILES_UPLOADNAME"))
					.filesType(rs.getString("FILES_TYPE"))
					.filesSize(rs.getLong("FILES_SIZE"))
					.build();
		}
		
	};
	private ResultSetExtractor<FilesDto> extractor = new ResultSetExtractor<FilesDto>() {

		@Override
		public FilesDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return FilesDto.builder()
						.filesNo(rs.getInt("FILES_NO"))
						.filesUploadname(rs.getString("FILES_UPLOADNAME"))
						.filesType(rs.getString("FILES_TYPE"))
						.filesSize(rs.getLong("FILES_SIZE"))
						.build();
			}
			else {
				return null;				
			}
		}
		
	};
	
	//시퀀스 번호 생성
	@Override
	public int sequence() {
		String sql = "select FILES_SEQ.NEXTVAL FROM DUAL";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	//등록
	@Override
	public void insert(FilesDto filesDto) {
		String sql = "insert into FILES("
				+ "FILES_NO, FILES_UPLOADNAME,"
				+ "FILES_TYPE, FILES_SIZE"
			+ ") values(?, ?, ?, ?)";
		Object[] param = {
				filesDto.getFilesNo(),
				filesDto.getFilesUploadname(),
				filesDto.getFilesType(),
				filesDto.getFilesSize()
		};
		jdbcTemplate.update(sql, param);
		
	}
	//목록조회
	@Override
	public List<FilesDto> selectList() {
		String sql="select * from FILES";
		return jdbcTemplate.query(sql, mapper);
	}
	//단일조회
	@Override
	public FilesDto selectOne(int filesNo) {
		String sql="select * from files "
				+ "where files_no = ?";
		Object[] param = {filesNo};
		return jdbcTemplate.query(sql, extractor, param);
	}
	//삭제
	@Override
	public boolean delete(int filesNo) {
		String sql="delete files where files_no = ?";
		Object[] param = {filesNo};
		return jdbcTemplate.update(sql,param)>0;
	}

}