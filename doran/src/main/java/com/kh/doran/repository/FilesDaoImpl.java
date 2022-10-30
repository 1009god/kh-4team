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
import com.kh.doran.vo.PjFileVO;
import com.kh.doran.vo.SellerFileVO;
import com.kh.doran.vo.profileImgVO;

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
		//번호를 프로그램에서 불러와서 쓸 때 필요하다
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
	
	
	
	private RowMapper<profileImgVO> mapper2= new RowMapper<profileImgVO>() {
		
		@Override
		public profileImgVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return profileImgVO.builder()
					.memNo(rs.getInt("mem_no"))
					.profileImgFileNo(rs.getInt("profile_img_file_no"))					
					.build();
		}
	};
	
	//mem 프로필 이미지
	@Override
	public void connectFiles(int filesNo, int memNo) {
		String sql = "insert into profile_img values ( ? , ? )";
		Object[] param = {filesNo, memNo};
		jdbcTemplate.update(sql,param);		
	}
	
	@Override
	public List<profileImgVO> profileImgList(int memNo) {
		String sql = "select * from profile_img_view where mem_no = ?";
		Object[] param = {memNo};
		return jdbcTemplate.query(sql, mapper2,param);
	}
	
	// 판매자 인증 첨부파일
	@Override
	public void connectSellerFiles(int sellerMemNo, int filesNo) {
		String sql = "insert into SF values( ? , ? )";
		Object[] param = {sellerMemNo, filesNo};
		jdbcTemplate.update(sql,param);			
	}
	
	@Override
	public List<FilesDto> selectNoticeFileList(int noticeFileNoticeNo) {
		String sql = "select * from notice_file_view "
							+ "where notice_file_notice_no = ?";
		Object[] param = {noticeFileNoticeNo};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	@Override
	public List<FilesDto> selectBoardFileList(int boardImgPostNo) {
		String sql = "select * from board_img_view "
				+ "where board_img_post_no = ?";
		Object[] param = {boardImgPostNo};
		return jdbcTemplate.query(sql, mapper, param);
	}
	
	
	@Override
	//프로젝트 첨부파일
	//컬럼을 전부 변수로 받아야 하는가
	//용도별 분류 컬럼을 어떻게 써야하는가
	public void connectPjFiles(int pjNo, int filesNo) {
		String sql ="insert into PJ_FILE(PJ_FILE_PJ_NO, PJ_FILE_NO, PJ_FILE_CLASSIFY) values(?,?,?)";
		Object[] param = {pjNo,filesNo,     };
		jdbcTemplate.update(sql,param);
		//List일 경우 맵퍼를 새로 짜야하는가
		//select * from PJ_FILES_VIEW where PJ_FILE_PJ_NO=?
	}
	@Override
	public List<PjFileVO> pjfile(int sellerMemNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private RowMapper<SellerFileVO> mapper3 = new RowMapper<SellerFileVO>() {
		
		@Override
		public SellerFileVO mapRow(ResultSet rs, int rowNum) throws SQLException {			
			return SellerFileVO.builder()
					.sellerMemNo(rs.getInt("seller_mem_no"))
					.sfFileNo(rs.getInt("sf_file_no"))
					.filesUploadName(rs.getString("FILES_UPLOADNAME"))
					.filesSize(rs.getInt("FILES_SIZE"))
					.filesType(rs.getString("FILES_TYPE"))
					.build();
		}
	};
	
	@Override
	public List<SellerFileVO> sellerFileList(int sellerMemNo) {
		String sql = "select * from seller_file_view where seller_mem_no = ?";
		Object[] param = {sellerMemNo};		
		return jdbcTemplate.query(sql,mapper3,param);
	}


	
	
	}
